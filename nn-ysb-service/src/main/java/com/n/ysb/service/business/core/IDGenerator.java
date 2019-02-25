package com.n.ysb.service.business.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.order.mapper.IDGeneratorMapper;
import com.n.ysb.service.util.MD5;

@Service
public class IDGenerator {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDGeneratorMapper IDGeneratorMapper;
	
	// 20位
	public String buildOrderNo() {
		String nextSeq = IDGeneratorMapper.getOrderSeqNext();
		String seq16 = addLeft80(nextSeq);
		String orderNo = "SK" + formatDate() + seq16;
		return orderNo;
	}
	
	// 20位
    public String buildWithdrawOrderNo() {
        String nextSeq = IDGeneratorMapper.getOrderSeqNext();
        String seq16 = addLeft80(nextSeq);
        String orderNo = "JS" + formatDate() + seq16;
        return orderNo;
    }
	
	// 16位
	public String buildMerchantCode() {
		String nextSeq = IDGeneratorMapper.getMerSeqNext();
		String seq4 = addLeft40(nextSeq);
		String orderNo = "MC" + formatDate() + seq4;
		return orderNo;
	}
	
	public String buildToken(String mobile, String pwd) {
		String token = MD5.md5("TK" + mobile + pwd + formatDate());
		return token;
	}
	
	public String buildCreditCardCode() {
		String nextSeq = IDGeneratorMapper.getOrderSeqNext();
		String seq16 = addLeft80(nextSeq);
		String orderNo = "CC" + formatDate() + seq16;
		return orderNo;
	}
	
	public String buildUserCode() {
		String nextSeq = IDGeneratorMapper.getUserSeqNext();
		return "U" + formatDate() + addLeft40(nextSeq);
	}
	
	public String buildRoleCode() {
		String nextSeq = IDGeneratorMapper.getRoleSeqNext();
		return "R" + formatDate() + addLeft40(nextSeq);
	}
	
	public String buildResCode() {
		String nextSeq = IDGeneratorMapper.getResSeqNext();
		return "P" + formatDate() + addLeft40(nextSeq);
	}
	
	// 20位
	public String buildTransferReqID() {
		String nextSeq = IDGeneratorMapper.getOrderSeqNext();
		String seq16 = addLeft80(nextSeq);
		String orderNo = "TS" + formatDate() + seq16;
		return orderNo;
	}
	
	private String formatDate() {
		final SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        return formatter.format(new Date());
	}
	
	private String formatDateHMS() {
		final SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        return formatter.format(new Date());
	}
	
	private String addLeft40(String nextSeq) {
		String baseStr ="0000";
		return baseStr.substring(0, 4-nextSeq.length()) + nextSeq;
	}
	
	private String addLeft80(String nextSeq) {
		String baseStr ="00000000";
		return baseStr.substring(0, 8-nextSeq.length()) + nextSeq;
	}
}
