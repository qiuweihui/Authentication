package com.cug.vehicle;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.cug.utils.Input;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * @author qiuweihui
 * @create 2020-11-02 14:38
 * 使用对称SM4密钥，对视频图片加密
 * 步骤8的前提
 */
public class ImageEncrypt {

    public static void main(String[] args) throws Exception {

        String sp = "D:\\TestData\\Vehicle\\ImageData\\001.mp4";//原始文件
        String dp = "D:\\TestData\\Vehicle\\EncryptData\\encrypt";//加密后文件，是否存放在行车记录仪中待讨论

        String key = Input.getString("D:\\TestData\\Vehicle\\sm4key.json");//读入SM4Key
        byte[] keyData = ByteUtils.fromHexString(key);
        //SM4加密文件
        encryptFile(keyData,sp,dp);

    }

    static{
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
            //No such provider: BC
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    //生成 Cipher

    public static Cipher generateCipher(int mode,byte[] keyData) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, java.security.InvalidKeyException {
        Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(keyData, "SM4");
        cipher.init(mode, sm4Key);
        return cipher;
    }

    //加密文件

    public static void encryptFile(byte[] keyData,String sourcePath,String targetPath){
        //加密文件
        try {
            Cipher cipher = generateCipher(Cipher.ENCRYPT_MODE,keyData);
            CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(sourcePath), cipher);
            FileUtil.writeFromStream(cipherInputStream, targetPath);
            IoUtil.close(cipherInputStream);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (FileNotFoundException | java.security.InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
