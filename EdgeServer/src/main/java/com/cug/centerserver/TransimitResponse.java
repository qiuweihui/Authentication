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
        //接收的内容暂定为视频存路径，为String，路径存入center_response.json
    }

}
