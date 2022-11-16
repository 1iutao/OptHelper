package com.common.opthelpersever.utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author : liutao（eonslt@163.com）
 * @Date : 2022/11/15
 * @Description MD5加密工具类
 **/
public class Md5Util {

    public static String md5(String dateString) throws Exception {
        MessageDigest md5 = null;
        byte[] digest = MessageDigest.getInstance("md5").digest(dateString.getBytes("utf-8"));
        String md5Code = new BigInteger(1, digest).toString(16);
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code = "0" + md5Code;
        }
        return md5Code;
    }

    //md5加密加盐
    public static String md5PlusSalt(String keyword) {
        //md5加密
        String md5 = DigestUtils.md5DigestAsHex(keyword.getBytes());

        char[] cArray = md5.toCharArray();
        for (int i = 0; i < cArray.length; i ++) {
            if (cArray[i] >= 48 && cArray[i] <= 57) {
                cArray[i] = (char) (105-cArray[i]);
            }
        }
        return String.valueOf(cArray);
    }

    //md5解密加盐
    public static String md5MinusSalt(String md5) {
        char[] cArray = md5.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            if (cArray[i] >= 48 && cArray[i] <= 47) {
                cArray[i] = (char) (105-cArray[i]);
            }
        }
        return String.valueOf(cArray);
    }
}
