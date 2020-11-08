package com.cug.vehicle;

import com.cug.utils.IOUtil;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:19
 * 对于视频图片数据的加密
 * 行车记录仪端计算哈希有三次，一次是初始计算自己公钥的hash，一次是核验服务器身份时计算服务器公钥的hash，一次是计算图像数据的hash
 * 分别为步骤1、6、8
 *
 */
public class HashCompute {
    public static void main(String[] args) throws IOException {
        //指定文件源，获得该文件的字节数组
        byte[] datas = IOUtil.fileToByteArray("D:\\TestData\\Vehicle\\");//计算的内容先转为字节数组
        String src = new String(datas);
        PrintStream ps = new PrintStream("D:\\TestData\\Vehicle\\hash_image.json");
        System.setOut(ps);                              //把创建的打印输出流赋给系统。即系统下次向 ps输出
        String s = generateSM3HASH(src);
        System.out.println( s);
    }

    public static String generateSM3HASH(String src) {
        byte[] md = new byte[32];
        byte[] msg1 = src.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        return s.toUpperCase();
    }

}



