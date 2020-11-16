package com.cug.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qiuweihui
 * @create 2020-11-10 10:27
 * 边缘服务器的服务器
 */
public class Server {

    //传入参数目前只有文件地址，添加广播部分后再添加IP地址
    public static void start(String outputPath) throws IOException {
        //1.创建服务器端的ServerSocket，指明自己的端口号
        ServerSocket ss = new ServerSocket(9090);
        //2.调用accept()表示接收来自于客户端的socket
        Socket socket = ss.accept();
        //3.获取输入流
        InputStream is = socket.getInputStream();
        //4.读取输入流中的数据,path代表输出路径
        FileOutputStream fos = new FileOutputStream(new File(outputPath));
        //5.关闭资源
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        //6.
        fos.close();
        is.close();
        socket.close();
        ss.close();

    }

}
