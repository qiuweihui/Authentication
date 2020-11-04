package SM4;

import cn.xjfme.encrypt.utils.sm4.SM4Utils;
import com.cug.utils.IOUtil;
import org.apache.commons.codec.binary.Base64;

import java.util.UUID;
/**
 * @author qiuweihui
 * @create 2020-10-29 19:19
 */
public class TestSM4 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        byte[] datas = IOUtil.fileToByteArray("D:\\TestData\\001.pdf");//文件转为字节数组
        String src = Base64.encodeBase64String(datas);
        //System.out.println("--生成SM4秘钥--");
        String sm4Key = generateSM4Key();
        long endTime1 = System.currentTimeMillis();    //获取结束时间
        System.out.println("生成密钥运行时间：" + (endTime1 - startTime) + "ms");    //输出程序运行时间
        System.out.println( sm4Key);
        // System.out.println("--CBC--");
   /*     String s1 = SM4EncForCBC(sm4Key, src);
        long endTime2 = System.currentTimeMillis();    //获取结束时间
        System.out.println("CBC加密运行时间：" + (endTime2 - endTime1) + "ms");    //输出程序运行时间
        String s2 = SM4DecForCBC(sm4Key, s1);
        long endTime3 = System.currentTimeMillis();    //获取结束时间
        System.out.println("CBC解密运行时间：" + (endTime3 - endTime2) + "ms");    //输出程序运行时间
        byte[] db2 = Base64.decodeBase64(s2);
        IOUtil.byteArrayToFile(db2, "D:\\TestData\\db2.pdf");//字节数组转文件*/
        // System.out.println("--ECB--");
        String s3 = SM4EncForECB(sm4Key, src);
        long endTime4 = System.currentTimeMillis();    //获取结束时间
        System.out.println("ECB加密运行时间：" + (endTime4 - endTime1) + "ms");    //输出程序运行时间
        String s4 = SM4DecForECB(sm4Key, s3);
        long endTime5 = System.currentTimeMillis();    //获取结束时间
        System.out.println("ECB解密运行时间：" + (endTime5 - endTime4) + "ms");    //输出程序运行时间
        byte[] db4 = Base64.decodeBase64(s4);
        IOUtil.byteArrayToFile(db4, "D:\\TestData\\ECB.pdf");//字节数组转文件
        long endTime6 = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime6 - startTime) + "ms");    //输出程序运行时间

    }

    public static String generateSM4Key() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    //对称秘钥加密(CBC)
    public static String SM4EncForCBC(String key,String text) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        sm4.iv = "31313131313131313131313131313131";
        String cipherText = sm4.encryptData_CBC(text);
        return cipherText;
    }

    //对称秘钥解密(CBC)
    public static String SM4DecForCBC(String key,String text) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        sm4.iv = "31313131313131313131313131313131";
        String plainText = sm4.decryptData_CBC(text);
        return plainText;
    }
    //对称秘钥加密(ECB)
    public static String SM4EncForECB(String key,String text) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        String cipherText = sm4.encryptData_ECB(text);
        return cipherText;
    }
    //对称秘钥解密(ECB)
    public static String SM4DecForECB(String key,String text) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        String plainText = sm4.decryptData_ECB(text);
        return plainText;
    }

}
