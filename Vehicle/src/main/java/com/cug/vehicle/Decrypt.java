package com.cug.vehicle;

import cn.xjfme.encrypt.utils.sm4.SM4Utils;
import com.cug.utils.IOUtil;
import org.apache.commons.codec.binary.Base64;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:22
 */
public class Decrypt {
    public static void main(String[] args) {   //CBC硬件加密

        byte[] datas = IOUtil.fileToByteArray("D:\\TestData\\Vehicle\\ImageData\\001.jpg");//文件转为字节数组
        String src = Base64.encodeBase64String(datas);
        //车辆端是读入服务器传来的SM4key，然后进行加密
        byte[] sm4keybyte = IOUtil.fileToByteArray("D:\\TestData\\Vehicle\\sm4key.txt");
        String sm4Key = Base64.encodeBase64String(sm4keybyte);  //读入SM4key并转为String
        System.out.println( sm4Key);
        String s1 = SM4EncForCBC(sm4Key, src);          //加密过程

       // String s2 = SM4DecForCBC(sm4Key, s1);

        byte[] db2 = Base64.decodeBase64(s1);
        IOUtil.byteArrayToFile(db2, "D:\\TestData\\Vehicle\\EncryptData\\encrypt");//字节数组转文件
    }


    //对称秘钥加密(CBC)
    public static String SM4EncForCBC(String key,String text) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
       // sm4.hexString = true;
        sm4.iv = "31313131313131313131313131313131";
        String cipherText = sm4.encryptData_CBC(text);
        return cipherText;
    }


}
