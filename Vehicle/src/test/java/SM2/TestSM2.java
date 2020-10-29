package SM2;

import com.cug.SM.SM2;
import com.cug.SM.SM2KeyPair;
import org.bouncycastle.math.ec.ECPoint;

import java.io.*;
import java.math.BigInteger;

/**
 * @author qiuweihui
 * @create 2020-10-27 22:22
 */
public class TestSM2 {
    public static void main(String[] args) throws FileNotFoundException {
        SM2 x = new SM2();
        SM2KeyPair keys = x.generateKeyPair();
        ECPoint pubKey = keys.getPublicKey();
        BigInteger privKey = keys.getPrivateKey();
        byte[] bytesdata = fileToByteArray("D:\\Test\\001.jpg");//原文件转为字节数组
        String stringdata = new String(bytesdata);                     //字节数组转为字符串String
        byte[] data = x.encrypt(stringdata, pubKey);    //加密读入string类型输出byte类型
        String res = new String(data);
        String origin = x.decrypt(data, privKey);       //解密读入byte类型输出string类型
        PrintStream ps = new PrintStream("D:\\Test\\key.txt");
        System.setOut(ps);                              //把创建的打印输出流赋给系统。即系统下次向 ps输出
        System.out.println(pubKey);
        System.out.println(privKey);
        PrintStream ps1 = new PrintStream("D:\\Test\\encrypt");
        System.setOut(ps1);                              //把创建的打印输出流赋给系统。即系统下次向 ps1输出
        System.out.println("encrypt: " + res);
        PrintStream ps2 = new PrintStream("D:\\Test\\decrypt");
        System.setOut(ps2);                               //把创建的打印输出流赋给系统。即系统下次向 ps2输出
        System.out.println("decrypt: " + origin);

    }
/*
    文件的读取操作，读取成byte[]字节数组
 */
    public static byte[] fileToByteArray(String filePath) {
        //创建源与目的地
        File src = new File(filePath);//获得文件的源头，从哪开始传入(源)
        byte[] dest = null;//最后在内存中形成的字节数组(目的地)
        //选择流
        InputStream is = null;//此流是文件到程序的输入流
        ByteArrayOutputStream baos = null;//此流是程序到新文件的输出流
        //操作(输入操作)
        try {
            is = new FileInputStream(src);//文件输入流
            baos = new ByteArrayOutputStream();//字节输出流，不需要指定文件，内存中存在
            byte[] flush = new byte[1024 * 10];//设置缓冲，这样便于传输，大大提高传输效率
            int len = -1;//设置每次传输的个数,若没有缓冲的数据大，则返回剩下的数据，没有数据返回-1
            while ((len = is.read(flush)) != -1) {
                baos.write(flush, 0, len);//每次读取len长度数据后，将其写出
            }
            baos.flush();//刷新管道数据
            dest = baos.toByteArray();//最终获得的字节数组
            return dest;//返回baos在内存中所形成的字节数组
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源,文件需要关闭,字节数组流无需关闭
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
