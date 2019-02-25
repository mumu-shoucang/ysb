package com.n.ysb.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

	public static void main(String[] args) {

		try {
			System.out.println(InetAddress.getByName("sms-cly.cn").getHostAddress());
			System.out.println(InetAddress.getByName("ccdcapi.alipay.com").getHostAddress());
			System.out.println(InetAddress.getByName("skb.yeepay.com").getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
