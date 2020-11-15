package com.cug.server;

import com.cug.utils.Server;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:20
 * 小车接收服务器返回的信息，角色为Server
 * 步骤5
 */
public class ResponseReceive extends Server {

    public static void main(String[] arge) throws IOException {

        ResponseReceive.start("D:\\TestData\\Vehicle\\response_receive.json");
        //服务器公钥，服务器签名，签名原文，都存在此Json对象中，需要时直接调用


    }
}
