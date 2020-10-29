package com.cug.vehicle;

import com.cug.SM.SM3;

import java.io.*;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:19
 */
public class HashCompute {
    public static void main(String[] args) throws IOException {

        System.out.println(SM3.byteArrayToHexString(SM3.hash("SM3 ".getBytes())));
    }
}

    /*
     * 获得指定文件的byte数组

    public byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            filePath = "D:\\video\\001.mp4";
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b))!= -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
     */

