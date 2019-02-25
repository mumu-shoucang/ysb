package com.newNet.lottery.client.sign.securityHelp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;


public class RSAToolImpl implements RSATool {

	public void generateKeyPair(File publicKeyFile, File privateKeyFile) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		SecureRandom random = new SecureRandom();
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

		generator.initialize(2048, random);

		KeyPair pair = generator.generateKeyPair();
		Key pubKey = pair.getPublic();

		PEMWriter pubWriter = new PEMWriter(new FileWriter(publicKeyFile));
		pubWriter.writeObject(pubKey); 
		pubWriter.close();
		
		PEMWriter privWriter = new PEMWriter(new FileWriter(privateKeyFile));
		privWriter.writeObject(pair); 
		privWriter.close();
	}

	public RSAKey loadPublicKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
		PEMReader reader = new PEMReader(new FileReader(file));
		Key pubKey = (Key) reader.readObject();
		reader.close();
		return new RSAKeyImpl(pubKey);
	}

	public RSAKey loadPrivateKey(File file) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		PEMReader reader = new PEMReader(new FileReader(file));
		KeyPair pair = (KeyPair)reader.readObject();
		Key privKey = (Key) pair.getPrivate();
		reader.close();
		return new RSAKeyImpl(privKey);
	}

	public byte[] encryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, ((RSAKeyImpl) key).getKey(),
				new SecureRandom());
		return cipher.doFinal(input);
	}

	public byte[] decryptWithKey(byte[] input, RSAKey key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, ((RSAKeyImpl) key).getKey());
		return cipher.doFinal(input);
	}

	public byte[] signWithKey(byte[] input, RSAKey key)
			throws NoSuchAlgorithmException, InvalidKeyException,
			SignatureException {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign((PrivateKey) ((RSAKeyImpl) key).getKey(),
				new SecureRandom());
		signature.update(input);
		return signature.sign();
	}

	public boolean verifyWithKey(byte[] input, byte[] sig, RSAKey key) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify((PublicKey) ((RSAKeyImpl) key).getKey());
		signature.update(input);
		return signature.verify(sig);
	}

}
