package com.newNet.lottery.client.sign.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESToolImpl implements AESTool  {

    public void generateAESKey(File aesClientKeyFile,File aesServerKeyFile)  throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(AESTool.ALGORITHM);  
        kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256  
        SecretKey sk = kg.generateKey();  
        byte[] b = sk.getEncoded();  
        FileOutputStream fos = new FileOutputStream(aesClientKeyFile);  
        fos.write(b);  
        fos.flush();  
        fos.close();  
        FileOutputStream fosServer = new FileOutputStream(aesServerKeyFile);  
        fosServer.write(b);  
        fosServer.flush();  
        fosServer.close();  
        
    }

    public String encryptWithKey(String originalStr, File file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
  {
        byte[] key = new byte[(int) file.length()];  
        FileInputStream fis = new FileInputStream(file);  
        fis.read(key);  
        SecretKeySpec sKeySpec = new SecretKeySpec(key, AESTool.ALGORITHM);  
        //Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");  
        Cipher cipher = Cipher.getInstance(AESTool.ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);  
        byte[] bjiamihou = cipher.doFinal(originalStr.getBytes("utf-8"));  
        return parseByte2HexStr(bjiamihou);  
        
        
    }

    public String decryptWithKey(String encryptStr, File file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = new byte[(int) file.length()];  
        FileInputStream fis = new FileInputStream(file);  
        fis.read(key);  
        SecretKeySpec sKeySpec = new SecretKeySpec(key, AESTool.ALGORITHM);  
        //Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        Cipher cipher = Cipher.getInstance(AESTool.ALGORITHM); 
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec); 
        byte [] encry = parseHexStr2Byte(encryptStr);
        byte[] bjiemihou = cipher.doFinal(encry);  
        return  new String (bjiemihou,"utf-8");  
    }
    public static void main(String[] args) {}
    /** 
     * 将二进制转换成16进制 
     * @method parseByte2HexStr 
     * @param buf 
     * @return 
     * @throws  
     * @since v1.0 
     */  
    public static String parseByte2HexStr(byte buf[]){  
        StringBuffer sb = new StringBuffer();  
        for(int i = 0; i < buf.length; i++){  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
    }  
      
    /** 
     * 将16进制转换为二进制 
     * @method parseHexStr2Byte 
     * @param hexStr 
     * @return 
     * @throws  
     * @since v1.0 
     */  
    public static byte[] parseHexStr2Byte(String hexStr){  
        if(hexStr.length() < 1)  
            return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
            result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
    }  
    
    
}
