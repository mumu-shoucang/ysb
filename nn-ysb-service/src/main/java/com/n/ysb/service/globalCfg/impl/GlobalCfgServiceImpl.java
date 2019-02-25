package com.n.ysb.service.globalCfg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.mapper.NnGlobalCfgMapper;
import com.n.ysb.service.globalCfg.po.NnGlobalCfg;
import com.n.ysb.service.globalCfg.po.NnGlobalCfgExample;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.FeeSetFlag;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.ProductType;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class GlobalCfgServiceImpl implements IGlobalCfgService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NnGlobalCfgMapper globalCfgMapper;
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Autowired
    private RedisTemplate<String, NnGlobalCfgVo> redis;
    
    private ExecutorService executor;
    private int cores = 0;
    @PostConstruct
    private void init() {
        cores = Runtime.getRuntime().availableProcessors() * 2;
        executor = Executors.newFixedThreadPool(cores);
    }
    
    @Override
    public NnGlobalCfgVo getGlobalCfg(){
        NnGlobalCfgExample example = new NnGlobalCfgExample();
        List<NnGlobalCfg> list = globalCfgMapper.selectByExample(example);
        
        if(list.size() == 0) {
        	return null;
        }
        
        NnGlobalCfg globalCfg = list.get(0);
        
        NnGlobalCfgVo globalCfgVo = new NnGlobalCfgVo();
        BeanUtils.copyProperties(globalCfg, globalCfgVo);
        
        return globalCfgVo;
    }

    @Override
    public void saveOrUpdateGlobalCfg(NnGlobalCfgVo globalCfgVo) {
        NnGlobalCfgVo queryGlobalCfgVo = this.getGlobalCfg();
        
        NnGlobalCfg globalCfg = new NnGlobalCfg();
        
        List<String> updateFeeFlag = new ArrayList<String>();
        
        if(queryGlobalCfgVo==null){
            updateFeeFlag.add(ProductType.trade_fee.getCode());
            updateFeeFlag.add(ProductType.t0_withdraw_fee.getCode());
            updateFeeFlag.add(ProductType.t0_withdraw_workday_fee.getCode());
            updateFeeFlag.add(ProductType.t0_withdraw_nonworkday_fee.getCode());
            
            BeanUtils.copyProperties(globalCfgVo, globalCfg);
            globalCfgMapper.insertSelective(globalCfg);
        }else{
            if(globalCfgVo.getTradeFee().compareTo(queryGlobalCfgVo.getTradeFee())!=0){
                updateFeeFlag.add(ProductType.trade_fee.getCode());
            }
            if(globalCfgVo.getT0WithdrawFee().compareTo(queryGlobalCfgVo.getT0WithdrawFee())!=0){
                updateFeeFlag.add(ProductType.t0_withdraw_fee.getCode());
            }
            if(globalCfgVo.getT0WithdrawWorkdayFee().compareTo(queryGlobalCfgVo.getT0WithdrawWorkdayFee())!=0){
                updateFeeFlag.add(ProductType.t0_withdraw_workday_fee.getCode());
            }
            if(globalCfgVo.getT0WithdrawNonworkdayFee().compareTo(queryGlobalCfgVo.getT0WithdrawNonworkdayFee())!=0){
                updateFeeFlag.add(ProductType.t0_withdraw_nonworkday_fee.getCode());
            }
            
            BeanUtils.copyProperties(globalCfgVo, queryGlobalCfgVo);
            BeanUtils.copyProperties(queryGlobalCfgVo, globalCfg);
            globalCfgMapper.updateByPrimaryKeySelective(globalCfg);
        }
        
        //奖费率放入redis
        this.setRedisGlobalCfgVo();
        
        //将本地设置的费率，同步到易宝
        if(updateFeeFlag.size()>0){
            this.feeSet(updateFeeFlag);
        }
    }
    
    private void feeSet(List<String> updateFeeFlag){
        //查询出所有的商户
        List<NnMerchantVo> allMerchant = merchantService.getAllMerchant();
        
        logger.info("开始设置费率==========================================");
        int size = allMerchant.size();
        
        int listStart,listEnd;
        if(cores>size){
            cores = size;
        }
        for (int i = 0; i < cores; i++) {  
            listStart = size / cores * i ;  
            listEnd = size / cores * ( i + 1 );
            
            if(i == cores - 1){  
                listEnd = size;  
            }  
            
            List<NnMerchantVo> subList = allMerchant.subList(listStart,listEnd);
            
            executor.submit(new FeeSetRunnable(subList, yeePayRequestService, updateFeeFlag));
        }
        
        
    }
    
    class FeeSetRunnable implements Runnable {

        private List<NnMerchantVo> merchants;
        private IYeePayRequestService yeePayRequestService;
        private List<String> updateFeeFlag;
        
        
        public FeeSetRunnable(List<NnMerchantVo> merchants, IYeePayRequestService yeePayRequestService, List<String> updateFeeFlag) {
            this.merchants = merchants;
            this.yeePayRequestService = yeePayRequestService;
            this.updateFeeFlag = updateFeeFlag;
        }
        
        @Override
        public void run() {
            for (NnMerchantVo merchantVo : merchants) {
//                yeePayRequestService.feeSet(merchantVo);
                this.updateFee(merchantVo);
            }
            long currentTimeMillis = System.currentTimeMillis();
            logger.info("feeSet finish========================"+merchants.size());
        }
        
        private void updateFee(NnMerchantVo merchantVo){
            //修改商户表状态为费率设置中
            merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_ING.getCode());
            merchantService.updateMerchantFeeInfo(merchantVo);
            
            String feeSetType = "";
            //交易费率处理
            if(updateFeeFlag.contains(ProductType.trade_fee.getCode())){
                //交易费率
                Map<String, Object> tradeFeeSet = yeePayRequestService.tradeFeeSet(merchantVo);
                if(tradeFeeSet.get("code").equals("0000")){
                    feeSetType+=ProductType.trade_fee.getCode();
                }
            }else{
                feeSetType+=ProductType.trade_fee.getCode();
            }
            
            //t0结算基本费率处理
            if(updateFeeFlag.contains(ProductType.t0_withdraw_fee.getCode())){
                //t0结算基本费率
                Map<String, Object> t0WithdrawFeeSet = yeePayRequestService.t0WithdrawFeeSet(merchantVo);
                if(t0WithdrawFeeSet.get("code").equals("0000")){
                    feeSetType+=ProductType.t0_withdraw_fee.getCode();
                }
            }else{
                feeSetType+=ProductType.t0_withdraw_fee.getCode();
            }
            
            //t0结算工作日额外费率处理
            if(updateFeeFlag.contains(ProductType.t0_withdraw_workday_fee.getCode())){
                //t0结算工作日额外费率
                Map<String, Object> t0WithdrawWorkdayFeeSet = yeePayRequestService.t0WithdrawWorkdayFeeSet(merchantVo);
                if(t0WithdrawWorkdayFeeSet.get("code").equals("0000")){
                    feeSetType+=ProductType.t0_withdraw_workday_fee.getCode();
                }  
            }else{
                feeSetType+=ProductType.t0_withdraw_workday_fee.getCode();
            }
            
            //t0结算非工作日额外费率处理
            if(updateFeeFlag.contains(ProductType.t0_withdraw_nonworkday_fee.getCode())){
                ////t0结算非工作日额外费率
                Map<String, Object> t0WithdrawNonworkdayFeeSet = yeePayRequestService.t0WithdrawNonworkdayFeeSet(merchantVo);
                if(t0WithdrawNonworkdayFeeSet.get("code").equals("0000")){
                    feeSetType+=ProductType.t0_withdraw_nonworkday_fee.getCode();
                }
            }else{
                feeSetType+=ProductType.t0_withdraw_nonworkday_fee.getCode();
            }
            
            ProductType[] values = ProductType.values();
            String productTypeListStr = "";
            for (ProductType productType : values) {
                productTypeListStr+=productType.getCode();
            }
            
            
            
            if(feeSetType.equals(productTypeListStr)){
                merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_SUCCESS.getCode());
            }else{
                merchantVo.setFeeSetFlag(FeeSetFlag.FEE_SET_FAIL.getCode());
            }
            
            merchantVo.setFeeSetType(feeSetType);
            merchantService.updateMerchantFeeInfo(merchantVo);
        }
        
    }
    
    @Override
    public NnGlobalCfgVo getRedisGlobalCfgVo(){
        NnGlobalCfgVo globalCfgVo = (NnGlobalCfgVo) redis.opsForHash().get("globalCfg", "globalCfg");
        if(globalCfgVo == null) {
        	globalCfgVo = this.getGlobalCfg();
        	if(globalCfgVo != null) {
        		doSetRedis(globalCfgVo);
        	}
        }
        return globalCfgVo;
    }
    
    public void doSetRedis(NnGlobalCfgVo redisGlobalCfgVo) {
        redis.opsForHash().put("globalCfg", "globalCfg", redisGlobalCfgVo);
    }
    
    @Override
    public void setRedisGlobalCfgVo(){
        NnGlobalCfgVo redisGlobalCfgVo = this.getGlobalCfg();
        doSetRedis(redisGlobalCfgVo);
    }
    
    public static void main(String[] args) {
        int ss = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println(ss);
        List<String> list = new ArrayList<String>();
        for(int i=1;i<=2;i++){
            list.add("数据"+i);
        }
        
        int size = list.size();
        int listStart,listEnd;
        if(ss>size){
            ss = size;
        }
        for (int i = 0; i < ss; i++) {  
            listStart = size / ss * i ;  
            listEnd = size / ss * ( i + 1 );
            
            if(i == ss - 1){  
                listEnd = size;  
            }  
            
            List<String> sunList = list.subList(listStart,listEnd); 
            System.out.println(sunList);
        }
        
        
    }
}