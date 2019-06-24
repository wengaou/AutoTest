package com.duobei.api.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class MD5Utils {


    /**
     *获取加密后的参数
     * @param param
     * @return
     */
    public static String getDigest(TreeMap<String, String> param){
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            buffer.append("&"+entry.getKey()+"="+entry.getValue());
        }

        buffer.append("94eeabd9b792421f95647b2fe5084f83");
        String plainText = buffer.toString();
        plainText = plainText.substring(1,plainText.length());
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }








}
