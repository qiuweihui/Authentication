package com.cug.server;

import com.alibaba.fastjson.JSONObject;
import com.cug.utils.Client;
import com.cug.utils.Input;
import com.cug.utils.Output;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:22
 * 发送事故广播寻找服务器，内容包括签名和公钥，角色为Client
 * 步骤2
 *
 **/
public class BroadcastSend extends Client {

    public static void main(String[] args) throws Exception {
        String jsonkey1 = Input.getString("D:\\TestData\\Vehicle\\pubkey.json");
        JSONObject jsonObject1 = JSONObject.parseObject(jsonkey1);
        String jsonkey2 = Input.getString("D:\\TestData\\Vehicle\\sign_vehicle.json");
        JSONObject jsonObject2 = JSONObject.parseObject(jsonkey2);
        String jsonkey3 = Input.getString("D:\\TestData\\Vehicle\\VID_Time.json");
        JSONObject jsonObject3 = JSONObject.parseObject(jsonkey3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(jsonObject1);
        jsonObject.putAll(jsonObject2);
        jsonObject.putAll(jsonObject3);
        Output.wirteText(String.valueOf(jsonObject),"D:\\TestData\\Vehicle\\broadcast_send.json");

        BroadcastSend.start("D:\\TestData\\Vehicle\\broadcast_send.json");

    }

}
