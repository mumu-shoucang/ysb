package com.newNet.lottery.client.sign.securityHelp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface RSATool {

	public void generateKeyPair(File publicKeyFile, File privateKeyFile) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException;

	public RSAKey loadPublicKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException;
	public RSAKey loadPrivateKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException;

	public byte[] encryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException;
	public byte[] decryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException;
	
	public byte[] signWithKey(byte[] input, RSAKey key) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
	public boolean verifyWithKey(byte[] input, byte[] signature, RSAKey key) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException;

}
