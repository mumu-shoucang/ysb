package com.n.ysb.web.order;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.orderLog.po.NnOrderLog;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("getOrderPage")
	@ResponseBody
	public SimpleResponse getOrderPage(NnOrderVo vo, 
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      )throws ParseException{
		PageInfo<NnOrder> orderPage = orderService.getOrderPage(vo, pageNo, limit);
		return SimpleResponse.suc(orderPage);
	}

	
	@RequestMapping("getOrderDetails")
	@ResponseBody
	public SimpleResponse getOrderDetails(String id){
		NnOrder order = orderService.getOrderDetails(id);
		return SimpleResponse.suc(order);
	}
	
	@RequestMapping("getOrderLogDetails")
	@ResponseBody
	public SimpleResponse getOrderLogDetails(String id){
		List<NnOrderLog> orderLogDetails = orderService.getOrderLogDetails(id);
		return SimpleResponse.suc(orderLogDetails);
	}
	
	@RequestMapping("withDraw")
    @ResponseBody
    public SimpleResponse withDraw(String id){
	    orderService.withDraw(id);
	    return SimpleResponse.suc(null);
	}
	@RequestMapping("export")
	@ResponseBody
	public SimpleResponse export(HttpServletResponse response,NnOrderVo vo){
		List<NnOrder> list = orderService.selectByExample(vo);
		BufferedOutputStream outputStream = null;
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		int bufferSize = 2048;
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("交易记录");
			String[] title = { "序号", "订单号",  "推荐人标识", "收款宝交易流水", "订单金额", "手续费","易宝T0自助结算基本手续费", 
					   "佣金金额","订单状态","下单时间","支付时间","结算时间"};
			int[] width = { 5*256, 22 * 256, 17 * 256, 20 * 256, 10 * 256,
					10 * 256, 17 * 256, 10 * 256, 10 * 256 ,20 * 256,
					20 * 256, 20 * 256};
			Row row3 = sheet.createRow(0);
			for (int i = 0; i < title.length; i++) {
				Cell cell = row3.createCell(i);
				cell.setCellValue(title[i]);
				sheet.setColumnWidth(i, width[i]);
			}
			//生成数据
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					NnOrder order = list.get(i);
					Row row5 = sheet.createRow(i+1);
					for (int j = 0; j < title.length; j++) {
						Cell cell = row5.createCell(j);
						String titleTemp = title[j];
						if("序号".equals(titleTemp)){
							cell.setCellValue(i + 1);
						}
						if("订单号".equals(titleTemp)){
							cell.setCellValue(order.getOrderNo());
						}
						if("推荐人标识".equals(titleTemp)){
							cell.setCellValue(order.getRefSign());
						}
						if("收款宝交易流水".equals(titleTemp)){
							cell.setCellValue(order.getYeeExternalLd());
						}
						if("订单金额".equals(titleTemp)){
							if(order.getOrderAmt()!=null){
								cell.setCellValue(order.getOrderAmt()+"");
							}else{								
								cell.setCellValue("0");
							}
						}
						if("手续费".equals(titleTemp)){
							if(order.getYeeTradeFee()!=null){
								cell.setCellValue(order.getYeeTradeFee()+"");
							}else{								
								cell.setCellValue("0");
							}
						}
						if("易宝T0自助结算基本手续费".equals(titleTemp)){
							if(order.getT0WithdrawFee()!=null){
								cell.setCellValue(order.getT0WithdrawFee()+"");
							}else{
								cell.setCellValue("0");
							}
						}
						if("佣金金额".equals(titleTemp)){
							if(order.getCommisstion()!=null){
								cell.setCellValue(order.getCommisstion()+"");
							}else{
								cell.setCellValue("0");
							}
						}
						if("订单状态".equals(titleTemp)){
//							if(order.getOrderStatus().equals(OrderStatus.init.getCode())){								
//								cell.setCellValue(OrderStatus.getDescByCode(order.getOrderStatus()));
//							}else if(order.getOrderStatus().equals("1")){
//								cell.setCellValue("支付中");
//							}else if(order.getOrderStatus().equals("2")){
//								cell.setCellValue("支付成功");
//							}else{
//								cell.setCellValue("结算成功");
//							}
						    cell.setCellValue(OrderStatus.getDescByCode(order.getOrderStatus()));
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if("下单时间".equals(titleTemp)){
							cell.setCellValue(sdf.format(order.getCreateDate()));
						}
						if("支付时间".equals(titleTemp)){
							if(order.getYeePayDate()!=null){								
								cell.setCellValue(sdf.format(order.getYeePayDate()));
							}
						}
						if("结算时间".equals(titleTemp)){
                            if(order.getYeeWithdrawHandleDate()!=null){                                
                                cell.setCellValue(sdf.format(order.getYeeWithdrawHandleDate()));
                            }
                        }
					}
				}
			}
			baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			bais = new ByteArrayInputStream(ba);
			String fileName = "交易记录_" + new SimpleDateFormat("yyyy-MM-dd").format
					(new Date()) + ".xlsx";
			String displayFileName = new String(fileName.getBytes("gb2312"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + displayFileName);
			outputStream = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buff = new byte[bufferSize];
			int bytesRead;
			while ((bytesRead = bais.read(buff, 0, buff.length)) != -1)

			{
				outputStream.write(buff, 0, bytesRead);
			}
			outputStream.flush();
			return SimpleResponse.suc(true);
		} catch (final IOException e) {
			return SimpleResponse.suc(false);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
