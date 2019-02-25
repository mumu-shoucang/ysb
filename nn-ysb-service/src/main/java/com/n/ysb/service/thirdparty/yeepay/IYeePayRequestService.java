package com.n.ysb.service.thirdparty.yeepay;

import java.util.Map;

import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.thirdparty.vo.BalanceType;
import com.n.ysb.service.thirdparty.vo.CustomerInforUpdateVo;
import com.n.ysb.service.thirdparty.vo.ReceiveVo;
import com.n.ysb.service.thirdparty.vo.RegisterVo;
import com.n.ysb.service.thirdparty.vo.TransferVo;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;

import net.sf.json.JSONObject;

public interface IYeePayRequestService {

    /**
     * 子商户注册
     * @param registerVo
     * @return 注册结果
     */
    Map<String, Object> register(RegisterVo registerVo);

    /**
     * 子商户信息修改
     * @param merchantVo
     * @return 修改结果
     */
    Map<String, Object> customerInforUpdate(CustomerInforUpdateVo customerInforUpdateVo);

    /**
     * 子商户交易费率设置
     * @param merchantVo
     * @return 设置结果
     */
    Map<String, Object> tradeFeeSet(NnMerchantVo merchantVo);

    /**
     * 子商户T0自助结算基本费率设置
     * @param merchantVo
     * @return 设置结果
     */
    Map<String, Object> t0WithdrawFeeSet(NnMerchantVo merchantVo);

    /**
     * 子商户T0自助结算工作日额外费率
     * @param merchantVo
     * @return 设置结果
     */
    Map<String, Object> t0WithdrawWorkdayFeeSet(NnMerchantVo merchantVo);

    /**
     * 子商户T0自助结算非工作日额外费率
     * @param merchantVo
     * @return 设置结果
     */
    Map<String, Object> t0WithdrawNonworkdayFeeSet(NnMerchantVo merchantVo);
    
    /**
     * 收款
     * @param merchantVo
     * @param receiveVo
     * @return请求接口结果
     */
    Map<String, Object> receive(NnMerchantVo merchantVo, ReceiveVo receiveVo);

    /**
     * 交易查询
     * @param merchantVo
     * @param orderVo
     * @return请求接口结果
     */
    JSONObject orderQuery(NnMerchantVo merchantVo, NnOrderVo orderVo);

    /**
     * 结算
     * @param merchantVo
     * @param withDrawVo
     * @return请求接口结果
     */
    Map<String, Object> withDraw(NnMerchantVo merchantVo, WithDrawVo withDrawVo, String withdrawType);

    /**
     * 结算查询
     * @param merchantVo
     * @param withDrawVo
     * @return请求接口结果
     */
    JSONObject withDrawQuery(NnMerchantVo merchantVo, WithDrawVo withDrawVo);

    /**
     * 费率设置
     * @param merchantVo
     */
    void feeSet(NnMerchantVo merchantVo);
    
    /**
     * 系统商返佣转账接口
     * 系统商给子商户返佣时，从系统商账户转账到子商户账户
     */
    Map<String, Object> transferToCustomer(TransferVo par);
    
    /**
     *  子商户余额查询接口
     *  可用余额类型: 1:T0 自助结算可用余额 2:T1 自助结算可用余额 3:账户余额
     */
    Map<String, Object> balanceQuery(String customerNumber, BalanceType balanceType);

}
