package com.cug.vehicle;

import com.alibaba.fastjson.JSONObject;
import com.cug.utils.Client;
import com.cug.utils.Input;
import com.cug.utils.Output;
/**
 * @author qiuweihui
 * @create 2020-10-27 21:41
 * 步骤5，对小车广播的回应，角色为Client
 * 发送内容为签名信息，加密信息,签名的原文内容
 */
public class BroadcastResponse extends Client {
    public static void main(String[] args) throws Exception {

        String jsonkey1 = Input.getString("D:\\TestData\\EdgeServer\\key_encrypt.json");
        JSONObject jsonObject1 = JSONObject.parseObject(jsonkey1);
        String jsonkey2 = Input.getString("D:\\TestData\\EdgeServer\\sign_server.json");
        JSONObject jsonObject2 = JSONObject.parseObject(jsonkey2);
        String jsonkey3 = Input.getString("D:\\TestData\\EdgeServer\\SID_Time.json");
        JSONObject jsonObject3 = JSONObject.parseObject(jsonkey3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(jsonObject1);
        jsonObject.putAll(jsonObject2);
        jsonObject.putAll(jsonObject3);
        Output.wirteText(String.valueOf(jsonObject),"D:\\TestData\\EdgeServer\\broadcast_response.json");

        BroadcastResponse.start("D:\\TestData\\EdgeServer\\broadcast_response.json");
    }



}
