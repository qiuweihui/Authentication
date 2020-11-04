package com.cug.chain;

import cn.hutool.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:19
 */
public class UpChain {
    public static final String ADD_URL = "http://mgds.mingbyte.com/carbaas/uploadVehicleKey";

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

            //connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject obj = new JSONObject();

            obj.put("vehicleId", "1001"); // VID

            obj.put("pubKeyHash", "AC26B3C8EE7265A495DB825D9FD8D85BB39851622D02F76615D57D307507CAB9"); //公钥哈希

            //System.out.println(obj.toString());

            //out.writeBytes(obj.toString());//这个中文会乱码
            out.write(obj.toString().getBytes("UTF-8"));//这样可以处理中文乱码问题
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
