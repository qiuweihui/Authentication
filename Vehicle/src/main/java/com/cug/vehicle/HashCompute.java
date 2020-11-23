package com.cug.vehicle;

import com.alibaba.fastjson.JSONObject;
import com.cug.utils.IOUtil;
import com.cug.utils.Input;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import java.util.Base64;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:19
 * 对于视频图片数据的加密
 * 行车记录仪端计算哈希有三次，一次是初始计算自己公钥的hash，一次是核验服务器身份时计算服务器公钥的hash，一次是计算图像数据的hash
 * 分别为步骤1、6、8
 *
 */
public class HashCompute {
    public static void main(String[] args) throws Exception {
        System.out.println(HashCompute.hashCompute("D:\\TestData\\Vehicle\\pubkey.json","pubkey"));
        //传入Json对象路径和Key值即可计算Value的hash
    }

    public static String hashCompute(String path , String key) throws Exception {

        String pubkey = Input.getString(path);
        JSONObject jsonObject = JSONObject.parseObject(pubkey);
        String src = jsonObject.getString(key);
        String s = generateSM3HASH(src);
        return s;
        //返回公钥hash值
    }
    public static String imageHashCompute(String path ) throws Exception {

        byte[] imageByte = IOUtil.fileToByteArray(path);
        String src = Base64.getEncoder().encodeToString(imageByte);
        String s = generateSM3HASH(src);
        return s;
        //返回图像数据的hash值
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

    @Test
    public void imageHashComputeTest() throws Exception {
        String hash =  imageHashCompute("D:\\TestData\\Vehicle\\ImageData\\TestVideo.avi");
        System.out.println(hash);
    }

}



