package com.duobei.seleniums;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class SuperPatrickTest {


    public interface SuperPatrickLibrary extends Library{

        void findElement(String pStrId,String pStrName,String pStrClassName,String controlType);
        void sendKeys(String pKeyString);
        void sendShortCutkeys(String pKeyString);

    }



    public static void main(String[] args){


        System.setProperty("jna.encoding","GBK");

        String dllPath = "/Applications/柏拉图教育(alpha).app/Contents/MacOS/柏拉图教育(alpha)";


        SuperPatrickLibrary superPatrickLibrary = (SuperPatrickLibrary) Native.loadLibrary(dllPath,SuperPatrickLibrary.class);




    }




}
