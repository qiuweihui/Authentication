package com.cug.chain;

import cn.hutool.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:41
 * 服务器验证小车公钥和VID是否已在区块链上
 * 步骤3
 * 根据返回内容，若为1，继续下一步，若为0，直接返回拒绝服务
 */
public class ChainCheck {
    public static final String ADD_URL = "http://mgds.mingbyte.com/carbaas/verifyVehicleKey";

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
            //边缘服务器端验证的是车的VID和公钥是否匹配
            obj.put("vehicleId", "1001");
            // VID

            obj.put("pubKeyHash", "AC26B3C8EE7265A495DB825D9FD8D85BB39851622D02F76615D57D307507CAB9");
            //公钥哈希，服务器公钥哈希，测试用，后面会调用HashCompute

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
        }

    }

    public static void main(String[] args) {
        appadd();
    }
}
