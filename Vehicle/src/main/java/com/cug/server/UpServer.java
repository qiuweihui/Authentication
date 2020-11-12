package com.cug.server;

import com.cug.utils.Client;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:20
 *上传加密过的视频、图片数据到边缘服务器
 *步骤8
 */
public class UpServer extends Client {
    public static void main(String[] args) throws IOException {
        UpServer.start("D:\\TestData\\Vehicle\\EncryptData");
        //加密图像数据在小车的存放路径
    }

}
