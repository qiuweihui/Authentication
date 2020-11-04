package com.cug.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author qiuweihui
 * @create 2020-11-02 16:34
 */
public class Input {

    public static String getString(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;

    }
}
