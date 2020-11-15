package com.cug.server;

import com.cug.utils.Server;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:25
 * 接收服务器发来的令牌
 * 步骤11
 */
public class TokenReceive extends Server {
    public static void main(String[] args) throws IOException {

        TokenReceive.start("D:\\TestData\\Vehicle\\Token.json");
    }
}
