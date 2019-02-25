package com.newNet.lottery.client.sign.crypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public interface AESTool {
    String ALGORITHM = "AES";
    /**
     * 
     * @param aesKeyFile   AES密钥存放路径
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
	public void generateAESKey(File aesClientKeyFile,File aesServerKeyFile) throws FileNotFoundException, IOException, NoSuchAlgorithmException ;
	/**
	 * 
	 * @param originalStr 需要加密的字符串
	 * @param key   AES密钥所有路径
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String encryptWithKey(String originalStr, File key) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
	/**
	 * 
     * @param encryptStr 加密之后的字符串
     * @param key   AES密钥所有路径
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decryptWithKey(String encryptStr, File key) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
	

}
