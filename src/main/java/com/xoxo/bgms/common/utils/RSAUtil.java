package com.xoxo.bgms.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package com.xoxo.bgms.common.utils
 * @Description RSA工具类
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-01-08 19:32
 */
public class RSAUtil {

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public static void main(String[] args) throws Exception {

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDK/FPHqHznXkZ7fkh9/Cb/DKaV9wrkJwp2ir0kywAbpIh4w9AjiOpVUrwxSJdUPI2JM/YMYmS1tJCPBfP/7KoAo6N+caydxilRX1DuH6O/zW/4nIfIGj3iAUtvdln6PdjSte/xXmh/BzMgVLpkOQe/6jkrp/CS+Q863ZGZ3qAUnwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMr8U8eofOdeRnt+SH38Jv8MppX3CuQnCnaKvSTLABukiHjD0COI6lVSvDFIl1Q8jYkz9gxiZLW0kI8F8//sqgCjo35xrJ3GKVFfUO4fo7/Nb/ich8gaPeIBS292Wfo92NK17/FeaH8HMyBUumQ5B7/qOSun8JL5DzrdkZneoBSfAgMBAAECgYBUvCzFJiKt+v71QmxEm2Q0CDXqWGxlfZ6yiYwXtd5fq7mEoElc7CTWZWlGMYVqlrSG1/gYh2J/L9dAvT10UnlMeHja0r3LvcrWckOOYqF0+4oCSw+yX4b2fXfAhZ7o2SSW9ktZzrdF3yTr8PqPYrHTzOSUd8Rkg3jpRGt6BnLZkQJBAO8oXmXGOMfG2Jv6DJe//GA6AqHBNQ8ZtIjo/BZt+JeoVxGUREpLglkjEF7qWDJozCe5JgagJl3aYCrrpW0pP5sCQQDZR9WfFhNJ5L5MCEqYFFnFqpykzMY3k5Y2iyfPZIaGHhENiE9aqHHBhMuTwtS2OrrNyBR8iA+Otdk+jaPgaolNAkAbEsvU46Kr4vPEfIMObrpoPkjr/M4/3l4UnaNj+qEIshLhN5KfgnrIEnMY+5huZHSwDoKif2C4gS8fzLtWZzP/AkABzbbYOzatxlhOCUOVP5X/Lgte0tRSYcc9bKpH7m2vb1B07Ey8U+md0Zxe/Z8GZDZovuBsqDR42smS21og4sGBAkEAvejYCm/+AGhQ+oK6Y/zKwIg+a7wgyDDOOXStPuYXPMmN71flgoVMU9MNS9ihH52YtyukEJI5+q6q51MXjkWERA==";
        System.out.println("publicKey：" + publicKey);
        System.out.println("privateKey：" + privateKey);
        Map strMap = new HashMap();
        strMap.put("merch_id", "99980001");
        strMap.put("tran_flowid", "1111111111111111");
        String jsonStr = JSONObject.toJSONString(strMap);
        System.out.println("---------------------------------------------");

        String sign = RSAUtil.signByPrivateKey(jsonStr, privateKey);
        System.out.println("sign：" + sign);
        System.out.println("验签：" + RSAUtil.verifySignByPublicKey(jsonStr, publicKey, sign));

        System.out.println("---------------------------------------------");
        String mingwen = "测试加密明文数据123rfasfas4323f";
        String cipherText = RSAUtil.encryptByPublicKey(jsonStr, RSAUtil.readPublicKeyFromString(publicKey));
        System.out.println("加密后：" + cipherText);
        String plainText = RSAUtil.decryptByPrivateKey(cipherText, RSAUtil.readPrivateKeyFromString(privateKey));
        System.out.println("解密后：" + plainText);
    }

    /**
     * 功能说明：生成公私钥对
     * @param filePath 生成文件路径
     */
    @SuppressWarnings("static-access")
    public static void getKeyPair(String filePath) {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = keyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String publicKeyString = Base64.encode(publicKey.getEncoded());
        String privateKeyString = Base64.encode(privateKey.getEncoded());

        try {
            BufferedWriter publicbw = new BufferedWriter(new FileWriter(new File(filePath + "/publicKey.keystore")));
            BufferedWriter privatebw = new BufferedWriter(new FileWriter(new File(filePath + "/privateKey.keystore")));
            publicbw.write(publicKeyString);
            privatebw.write(privateKeyString);
            publicbw.flush();
            publicbw.close();
            privatebw.flush();
            privatebw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能说明：从文件中读取公钥或私钥
     * @param filePath 文件路径
     * @return 公钥或私钥
     */
    public static String readKeyFromFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String readLine = null;
            StringBuilder sb = new StringBuilder();

            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 功能说明：从字符串中加载公钥
     * @param publicKeyStr
     * @return 公钥
     */
    public static RSAPublicKey readPublicKeyFromString(String publicKeyStr) {
        try {
            byte[] bt = Base64.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bt);
            return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 功能说明：从字符串中加载私钥
     * @param privateKeyStr
     * @return 私钥
     */
    public static RSAPrivateKey readPrivateKeyFromString(String privateKeyStr) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 功能说明：私钥加签
     * @param content 报文
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA256withRSA");// MD5withRSA
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] sign = signature.sign();
            return Base64.encode(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能说明： 公钥验签
     * @param content 报文
     * @param publicKey 公钥
     * @param sign 签名值
     * @return 验签是否通过
     */
    public static boolean verifySignByPublicKey(String content, String publicKey, String sign) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA256withRSA");// MD5withRSA
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 功能说明：公钥加密
     * @param plainText 明文
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception
     */
    public static String encryptByPublicKey(String plainText, RSAPublicKey publicKey) throws Exception {
        if (publicKey == null) {
            throw new Exception("公钥为空！");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainText.getBytes());
            return Base64.encode(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能说明：私钥解密
     * @param cipherText 密文
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception
     */
    public static String decryptByPrivateKey(String cipherText, RSAPrivateKey privateKey) throws Exception {
        if (privateKey == null) {
            throw new Exception("私钥为空！");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(Base64.decode(cipherText));
            return new String(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}