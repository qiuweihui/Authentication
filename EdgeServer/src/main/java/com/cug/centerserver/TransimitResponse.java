package com.cug.centerserver;

import com.cug.utils.Server;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 22:15
 *
 *接收中心服务器的回应，角色为Server
 *
 */
public class TransimitResponse extends Server {

    public static void main(String[] arge) throws IOException {
        TransimitResponse.start("D:\\TestData\\EdgeServer\\center_response.json");
    }

}
