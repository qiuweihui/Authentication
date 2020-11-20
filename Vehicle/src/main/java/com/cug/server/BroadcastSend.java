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
        String json1 = Input.getString("D:\\TestData\\Vehicle\\pubkey.json");
        JSONObject jsonObject1 = JSONObject.parseObject(json1);
        String json2 = Input.getString("D:\\TestData\\Vehicle\\sign_vehicle.json");
        JSONObject jsonObject2 = JSONObject.parseObject(json2);
        //sign_vehicle包含了签名和原文

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(jsonObject1);
        jsonObject.putAll(jsonObject2);
        Output.wirteText(String.valueOf(jsonObject),"D:\\TestData\\Vehicle\\broadcast_send.json");
        //发送的内容先合并再发送
        BroadcastSend.start("D:\\TestData\\Vehicle\\broadcast_send.json");

    }

}
