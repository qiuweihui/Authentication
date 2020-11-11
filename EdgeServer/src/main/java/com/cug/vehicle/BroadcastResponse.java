package com.cug.vehicle;

import com.cug.utils.Client;
import java.io.IOException;
/**
 * @author qiuweihui
 * @create 2020-10-27 21:41
 * 步骤5，对小车广播的回应，角色为Client
 */
public class BroadcastResponse extends Client {
    public static void main(String[] args) throws IOException {//包括签名，签名原文，加密内容
        BroadcastResponse.start();
    }

}
