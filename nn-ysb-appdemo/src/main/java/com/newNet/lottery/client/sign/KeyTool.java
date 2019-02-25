package com.newNet.lottery.client.sign;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import com.newNet.lottery.client.sign.securityHelp.RSAKey;
import com.newNet.lottery.client.sign.securityHelp.RSATool;
import com.newNet.lottery.client.sign.securityHelp.RSAToolFactory;

/**
 * 签名验签工具类.
 * <p>
 * 
 * @author kehy
 */
public class KeyTool {

    // private RSAPrivateKey privateKey;
    // private RSAPublicKey publicKey;

    private RSAKey privateKey;
    private RSAKey publicKey;

    public KeyTool(String keydir) {
        File privateFile = new File(keydir + "private.key");
        File publicFile = new File(keydir + "public.key");

        RSATool tool = RSAToolFactory.getRSATool();
        try {
            privateKey = tool.loadPrivateKey(privateFile);
            publicKey = tool.loadPublicKey(publicFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String sign(String str) {
        String rlt = null;
        try {
            byte[] obj = str.getBytes("UTF-8");
            RSATool tool = RSAToolFactory.getRSATool();
            byte[] bytes = tool.signWithKey(obj, privateKey);
            return Helper.bytesToString(bytes);

        } catch (NoSuchAlgorithmException e) {
            throw new SignException();
        } catch (InvalidKeyException e) {
            throw new SignException();
        } catch (UnsupportedEncodingException e) {
            throw new SignException();
        } catch (SignatureException e) {
            throw new SignException();
        }
    }

    public boolean verify(String str, String sign) {
        try {
            RSATool tool = RSAToolFactory.getRSATool();
            byte[] obj = str.getBytes("GBK");
            return tool.verifyWithKey(obj, Helper.hexToBytes(sign), publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new SignException();
        } catch (InvalidKeyException e) {
            throw new SignException();
        } catch (UnsupportedEncodingException e) {
            throw new SignException();
        } catch (SignatureException e) {
            throw new SignException();
        }
    }

}
