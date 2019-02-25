package com.newNet.lottery.client.sign.crypt;

import java.security.Security;


public class AESToolFactory {

	private static AESTool instance = null;
	
	public static AESTool getAESTool() {
		if(instance == null) {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());			
			instance = new AESToolImpl();
		}
		return instance;
	}

}
