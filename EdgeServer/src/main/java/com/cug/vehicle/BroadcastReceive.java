package com.cug.vehicle;

import com.cug.utils.Server;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:40
 * 服务器接收小车发来的广播，并存储广播内容，角色为Server
 *
 */
public class BroadcastReceive extends Server{
    public static void main(String[] arge) throws IOException {
        BroadcastReceive.start("D:\\TestData\\EdgeServer\\sign_vehicle.json");
       // BroadcastReceive.start("D:\\TestData\\EdgeServer\\pubkey_vehicle,json");
        //包括签名和小车公钥，分别存在sign_vehicle和pubkey_vehicle
    }

}
