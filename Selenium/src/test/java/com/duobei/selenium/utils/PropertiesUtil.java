package com.duobei.selenium.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private Properties pro;
    private String filePath;

    /**
     * 构造方法
     * @return
     * @throws IOException
     */
    public PropertiesUtil(String filePath){
        this.filePath = "./src/main/resources/"+filePath;
        this.pro = readProperties();
    }

    /**
     * 读取配置文件
     * @return
     * @throws IOException
     */
    //"./src/main/resources/element.properties"
    private Properties readProperties(){
        Properties properties = new Properties();
        try {
            InputStream inputStream1 = new FileInputStream(filePath);
            InputStream inputStream = new BufferedInputStream(inputStream1);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }



    public String getProperties(String key)  {
        if (pro.containsKey(key)) {
            String kyes = pro.getProperty(key);
            //System.out.println(kyes);
            return kyes;
        }else {
            System.out.println("你获取的key值不对");
            return "";
        }

    }





}
