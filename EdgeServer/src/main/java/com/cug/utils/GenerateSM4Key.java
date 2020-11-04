package com.cug.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import java.security.Security;

import static cn.hutool.crypto.symmetric.SM4.ALGORITHM_NAME;

/**
 * @author qiuweihui
 * @create 2020-11-02 15:24
 */
public class GenerateSM4Key {
    public static final int KEY_SIZE = 128;

    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }
    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {

        //生成Key并输出到指定位置存储
        byte[] bytes = generateKey(KEY_SIZE);
        String key = ByteUtils.toHexString(bytes);
        Output.wirteText(key,"D:\\TestData\\EdgeServer\\sm4key.txt");

    }
}
