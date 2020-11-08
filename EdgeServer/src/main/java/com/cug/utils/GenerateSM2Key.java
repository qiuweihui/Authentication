package com.cug.utils;

import cn.hutool.json.JSONObject;
import cn.xjfme.encrypt.utils.sm2.SM2EncDecUtils;
import cn.xjfme.encrypt.utils.sm2.SM2KeyVO;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-11-02 19:15
 *
 * 用于服务器端产生SM2密钥，包括公钥和私钥
 * 预备步骤，只在初始化时执行一次
 */
public class GenerateSM2Key {
    public static void main(String[] args) throws IOException {

        SM2KeyVO sm2KeyVO = generateSM2Key();
        JSONObject jsonkey =new  JSONObject();
        jsonkey.accumulate("pubkey",sm2KeyVO.getPubHexInSoft());
        jsonkey.accumulate("prikey",sm2KeyVO.getPriHexInSoft());

        Output.wirteText(String.valueOf(jsonkey),"D:\\TestData\\EdgeServer\\key.json");//生成的公私钥
    }

    public static SM2KeyVO generateSM2Key() throws IOException {
        SM2KeyVO sm2KeyVO = SM2EncDecUtils.generateKeyPair();
        return sm2KeyVO;
    }
}
