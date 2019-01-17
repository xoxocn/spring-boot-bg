/**
 * @Title: VerifySignUtil.java
 * @Package: com.zshy.infocoin.common.utils
 * @author LZG, liuzhongguochn@gmail.com
 * Copyright (c) 2018 北京中数合一科技有限公司
 */
package com.xoxo.bgms.common.utils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author LZG
 * @description: 验签工具总匝。
 * --结合http请求对MD5Util、RSAUtil的再次封装
 * @date 2018/10/19
 */
public class VerifySignUtil {

    public static boolean md5Verify(HttpServletRequest request) {

        String md5Key = MD5Util.md5Key;

        //签名验空
        String clientSignInfo = request.getParameter("signInfo");
        if (StringUtils.isEmpty(clientSignInfo))
            return false;

        Map<String, Object> paraMap = new HashMap<String, Object>();

        //获取所有参数
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            if (paraName.equals("signInfo"))
                continue;

            paraMap.put(paraName, request.getParameter(paraName));
        }

        String paraJson = LinkStrUtil.createLinkString(paraMap, true);
        boolean verifyResult = MD5Util.verify(paraJson, clientSignInfo, md5Key);
        return verifyResult;

    }

//    public static ResponseVo md5Verify(Map<String, Object> paraMap, String signInfo) {
//
//        String md5Key = SignKeyPropertiesUtil.getMd5Key();
//
//        //签名验空
//        if (StringUtils.isEmpty(signInfo))
//            return ResponseVo.build(ResponseEnum.param_blank, "signInfo");
//
//        String paraJson = LinkStrUtil.createLinkString(paraMap, true);
//        boolean verifyResult = MD5Util.verify(paraJson, signInfo, md5Key);
//
//        if (!verifyResult)
//            return ResponseVo.build(ResponseEnum.sign_fail);
//        return ResponseVo.build(ResponseEnum.success);
//
//    }

//    /**
//     * 生成签名  使用AES加密，用MD5(32)进行签名(发送短信验证码)
//     *
//     * @param data 待签名数据
//     * @param key  API密钥
//     * @return 签名
//     */
//    public static String getSign(final Map<String, String> data, String key) {
//        String result = "";
//        try {
//            Set<String> keySet = data.keySet();
//            String[] keyArray = keySet.toArray(new String[keySet.size()]);
//            Arrays.sort(keyArray);
//            StringBuilder sb = new StringBuilder();
//            for (String k : keyArray) {
//                if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//                    sb.append(k).append("=").append(data.get(k).trim()).append("&");
//            }
//            String str = sb.toString().substring(0, sb.toString().length() - 1);
//            String dataStr = AESUtil.encrypt(str, key);   //使用AES加密，生成一个加密后的data
//            result = MD5Util.digest(dataStr);          //用MD5对data进行签名得到signInfo
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return result;
//    }

}