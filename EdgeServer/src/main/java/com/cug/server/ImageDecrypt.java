package com.cug.server;

import cn.hutool.core.io.IoUtil;
import com.cug.utils.Input;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:42
 * 视频图片数据的解密功能，使用SM4对称密钥解密
 * 步骤8、9之间
 */
public class ImageDecrypt {

    public static void main(String[] args) throws Exception {

        String dp = "D:\\TestData\\EdgeServer\\EncryptData\\encrypt";
        //加密后文件
        String dp2 = "D:\\TestData\\EdgeServer\\DecryptData\\decrypt";
        //解密后文件，指定文件格式，如.jpg.mp4可直接播放
        String key = Input.getString("D:\\TestData\\Vehicle\\sm4key.json");
        byte[] keyData = ByteUtils.fromHexString(key);
        long startTime = System.currentTimeMillis();

        //解密文件
        decryptFile(keyData, dp, dp2);
        long endTime2 = System.currentTimeMillis();
        //获取结束时间
        System.out.println("解密文件时间：" + (endTime2 - startTime) + "ms");
        //输出程序运行时间
    }


    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            //No such provider: BC
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    //生成 Cipher

    public static Cipher generateCipher(int mode, byte[] keyData) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, java.security.InvalidKeyException {
        Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(keyData, "SM4");
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * 解密文件
     *
     * @param sourcePath 待解密的文件路径
     * @param targetPath 解密后的文件路径
     */
    public static void decryptFile(byte[] keyData, String sourcePath, String targetPath) {
        FileInputStream in = null;
        ByteArrayInputStream byteArrayInputStream = null;
        OutputStream out = null;
        CipherOutputStream cipherOutputStream = null;
        try {
            in = new FileInputStream(sourcePath);
            byte[] bytes = IoUtil.readBytes(in);
            byteArrayInputStream = IoUtil.toStream(bytes);

            Cipher cipher = generateCipher(Cipher.DECRYPT_MODE, keyData);

            out = new FileOutputStream(targetPath);
            cipherOutputStream = new CipherOutputStream(out, cipher);
            IoUtil.copy(byteArrayInputStream, cipherOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(cipherOutputStream);
            IoUtil.close(out);
            IoUtil.close(byteArrayInputStream);
            IoUtil.close(in);
        }
    }
}

