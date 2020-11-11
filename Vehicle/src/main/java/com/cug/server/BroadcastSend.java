package com.cug.server;

import com.cug.utils.Client;
import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:22
 * 发送事故广播寻找服务器，内容包括签名和公钥，角色为Client
 * 步骤2
 *
 **/
public class BroadcastSend extends Client {

        // 发送的文件存储路径
    public static void main(String[] args) throws IOException {
        BroadcastSend.start("D:\\TestData\\Vehicle\\sign_self.json");
       // BroadcastSend.start("D:\\TestData\\Vehicle\\sm4key.json");
    }

}
