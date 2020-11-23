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
 * @create 2020-11-09 20:48
 * 将事故产生的文本和hash数据上链
 * 步骤8’
 */
public class UpLoadInfo {

    public static final String ADD_URL = "http://mgds.mingbyte.com/carbaas/uploadAccidentInfo";

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
            long timestamp = System.currentTimeMillis();
            String jsonVID = Input.getString("D:\\TestData\\Vehicle\\VID.json");
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(jsonVID);
            String VID = jsonObject.getString("VID");
            obj.put("vehicleId",VID);
            obj.put("eventId", "20201101235959");
            obj.put("createTime", timestamp);
            obj.put("videohash", HashCompute.imageHashCompute
                    ("D:\\TestData\\Vehicle\\ImageData\\TestVideo.avi"));
            //视频hash，传入地址，请确保测试视频名称一致
            String jsonText = Input.getString("D:\\TestData\\Vehicle\\text_data.json");
            com.alibaba.fastjson.JSONObject textData = com.alibaba.fastjson.JSONObject.parseObject(jsonText);
            obj.putAll(textData);
            //添加text数据，如海拔，经纬度，加速度

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
