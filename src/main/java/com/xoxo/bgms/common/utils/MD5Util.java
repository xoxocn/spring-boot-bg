/**
 * @Title: MD5Util.java
 * @Package: com.zshy.infocoin.common.utils
 * @author LZG, liuzhongguochn@gmail.com
 * Copyright (c) 2018 北京中数合一科技有限公司
 */
package com.xoxo.bgms.common.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:    MD5工具类
 * @Author:         xiehua@zhongshuheyi.com
 * @CreateDate:     2019/1/5 14:19
 */
public class MD5Util {

    public final static String md5Key ="xoxo";

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    //测试
    public static void main(String[] args) {
        //MD5加签验签测试
        String key = "liuzhongguo@gmail.com";
        String text = "测试";
        String sign = sign(text, key);
        System.out.println("加签：" + sign);
        System.out.println("验签：" + verify(text, sign, key));

        System.out.println("------------------------");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("accountId", "admin");
        paramMap.put("name", "刘德华");
        paramMap.put("device", "android");
        paramMap.put("note", "hello");

        @SuppressWarnings("unused")
        String jsonStr = LinkStrUtil.createLinkString(paramMap, true);
        String sign2 = sign(text, key);
        System.out.println("加签：" + sign2);
        System.out.println("验签：" + verify(text, sign2, key));


    }

    /**
     * @param text          需要签名的字符串
     * @param key           密钥
     * @param input_charset 编码格式
     * @return java.lang.String 签名结果
     * @Description: 签名字符串
     * @author LZG
     * @date 2018/10/15
     */
    public static String sign(String text, String key) {
        text = text + "&key=" + key;
        return DigestUtils.md5DigestAsHex(getContentBytes(text, "UTF-8"));
    }


    /**
     * @param text          需要签名的字符串
     * @param input_charset
     * @return java.lang.String 编码格式
     * @Description: 签名字符串
     * @author LZG
     * @date 2018/10/15
     */
    @SuppressWarnings("unused")
    private static String signByCharset(String text, String input_charset) {
        return DigestUtils.md5DigestAsHex(getContentBytes(text, input_charset));
    }

    /**
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param input_charset 编码格式
     * @return boolean 验签是否通过
     * @Description: 验签
     * @author LZG
     * @date 2018/10/15
     */
    public static boolean verify(String text, String sign, String key) {
        text = text + "&key=" + key;
        String mySign = DigestUtils.md5DigestAsHex(getContentBytes(text, "UTF-8"));
        System.out.println(text);
        System.out.println(mySign);
        if (mySign.equals(sign)) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return byte[]
     * @Description: 得到内容bytes(private)
     * @author LZG
     * @date 2018/10/15
     */
    private static byte[] getContentBytes(String content, String charset) {
        if ((charset == null) || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) { //编码转换异常
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


    /**
     * MD5 摘要计算(byte[]).
     *
     * @param src byte[]
     * @return String
     * @throws Exception
     */
    public static String digest(String src) {
        try {
            return digest(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5 摘要计算(byte[]).
     *
     * @param src byte[]
     * @return String
     * @throws Exception
     */
    public static String digest(byte[] src) {
        MessageDigest alg;
        try {
            alg = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(alg.digest(src));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}