package com.cug.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author qiuweihui
 * @create 2020-11-09 20:18
 * 小车端的客户端
 */
public class Client {
    public static void main(String[] args) throws IOException, Exception {
            Client.start("D:\\TestData\\Vehicle\\","123.123");
    }
        //传入参数目前只有文件地址，后再添加IP地址
    public static void start(String inputPath, String iP) throws IOException{
        //1.创建Socket对象，指明服务器端的ip和端口号
        Socket socket = new Socket(InetAddress.getByName(iP),9090);
        //172.27.40.221
        //2.获取一个输出流，用于输出数据
        OutputStream os = socket.getOutputStream();
        //3.写出数据的操作
        FileInputStream fis = new FileInputStream(new File(String.valueOf(inputPath)));
        //4.资源的关闭
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        //5.
        fis.close();
        os.close();
        socket.close();
    }

}
