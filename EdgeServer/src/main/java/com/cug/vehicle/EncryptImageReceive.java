package com.cug.vehicle;

import com.cug.utils.Server;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:42
 */
public class EncryptImageReceive extends Server{

    public static void main(String[] arge) throws IOException {
        EncryptImageReceive.start("D:\\TestData\\EdgeServer\\EncryptData\\encrypt");
        //接收加密的数据，数据可以自增编号
    }
}
