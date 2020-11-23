package com.cug.chain;

import cn.hutool.json.JSONObject;
import com.cug.utils.Input;
import com.cug.vehicle.HashCompute;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:40
 * 步骤1‘，上传服务器公钥hash和SID到区块链
 * 只在初始化时执行一次
 */
public class UpChain {
    public static final String ADD_URL = "http://mgds.mingbyte.com/carbaas/uploadServerKey";

    public static void appadd() {

        try{
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject obj = new JSONObject();

            //读入并添加SID
            String jsonSID = Input.getString("D:\\TestData\\EdgeServer\\SID.json");
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(jsonSID);
            String SID = jsonObject.getString("SID");
            obj.put("serverId",SID);
            obj.put("pubKeyHash", HashCompute.hashCompute("D:\\TestData\\EdgeServer\\pubkey.json","pubkey"));
            //上传服务器公钥哈希
            System.out.println(obj);
            out.write(obj.toString().getBytes("UTF-8"));
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        appadd();
    }
}
