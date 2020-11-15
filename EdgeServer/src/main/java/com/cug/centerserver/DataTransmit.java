package com.cug.centerserver;

import com.cug.utils.Client;
import java.io.IOException;


/**
 * @author qiuweihui
 * @create 2020-10-27 21:43
 *
 * 边缘服务器将视频图片数据传送到中心服务器,角色为Client
 * 步骤9
 */
public class DataTransmit extends Client {

    public static void main(String[] args) throws IOException {
        DataTransmit.start("D:\\TestData\\EdgeServer\\DecryptData\\decrypt.avi");
        //转发已解密的视频到中心服务器
    }

}
