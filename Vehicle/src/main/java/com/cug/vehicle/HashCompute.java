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
 */
public class HashCompute {
    public static void main(String[] args) throws IOException {
        //指定文件源，获得该文件的字节数组
        byte[] datas = IOUtil.fileToByteArray("D:\\TestData\\Vehicle\\ImageData\\001.jpg");//文件转为字节数组
        String src = new String(datas);
        PrintStream ps = new PrintStream("D:\\TestData\\Vehicle\\hash_image.json");
        System.setOut(ps);                              //把创建的打印输出流赋给系统。即系统下次向 ps输出
        //System.out.println("--SM3摘要测试--");
        String s = generateSM3HASH(src);
        System.out.println( s);
        //System.out.println("--SM3摘要结束--");
    }

    public static String generateSM3HASH(String src) {
        byte[] md = new byte[32];
        byte[] msg1 = src.getBytes();
        //System.out.println(Util.byteToHex(msg1));
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        return s.toUpperCase();
    }

}



