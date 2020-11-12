package com.cug.server;

import com.cug.utils.IOUtil;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:40
 *
 * 边缘服务器端计算哈希有两次，一次是初始计算自己公钥的hash，一次是核验小车身份时计算小车公钥的hash
 * 预备步骤，步骤3都会用到
 * hash值不需要输出存储，只在需要计算时调用
 *
 */
public class HashCompute {
    public static String main(String[] args) throws IOException {
        //指定文件源，获得该文件的字节数组
        byte[] datas = IOUtil.fileToByteArray("D:\\TestData\\EdgeServer\\");
        //需要计算的内容（公钥）转为字节数组
        String src = new String(datas);
        String s = generateSM3HASH(src);
        return s;
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
