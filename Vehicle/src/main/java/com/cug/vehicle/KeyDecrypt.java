package com.cug.vehicle;

import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2EncDecUtils;
import com.cug.utils.Input;
import com.cug.utils.Output;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-11-02 14:38
 * 小车用自己的私钥解密出服务器发过来的加密内容（服务器的公钥，SM4Key），其中服务器公钥用于到区块链验证，SM4Key用于加密数据
 * 步骤6、8的前提
 */
public class KeyDecrypt {

    public static void main(String[] args) throws Exception {

        String prikey = jsonToString("D:\\TestData\\Vehicle\\prikey.json","prikey");
        //读入小车的SM2私钥
        String encrypt = jsonToString("D:\\TestData\\Vehicle\\response_receive.json","encrypt");
        //读入服务器发来的加密内容

        String decryptInfo = SM2Dec(prikey,encrypt);
        //解密出SM4Key和服务器公钥，分别存储，输出为json对象存储
        String pubkey_server = decryptInfo.substring(0,130);
        String sm4key = decryptInfo.substring(130);
        cn.hutool.json.JSONObject json1 =new cn.hutool.json.JSONObject();
        json1.accumulate("pubkey",pubkey_server);
        Output.wirteText(String.valueOf(json1),"D:\\TestData\\Vehicle\\pubkey_server.json");
        cn.hutool.json.JSONObject json2 =new cn.hutool.json.JSONObject();
        json2.accumulate("sm4key",sm4key);
        Output.wirteText(String.valueOf(json2),"D:\\TestData\\Vehicle\\sm4key.json");

    }
    //私钥解密
    public static String SM2Dec(String priKey, String encryptedData) throws IOException {
        //填充04
        encryptedData="04"+encryptedData;
        byte[] decrypt = SM2EncDecUtils.decrypt(Util.hexStringToBytes(priKey), Util.hexStringToBytes(encryptedData));
        return new String(decrypt);
    }
    public static String jsonToString(String path , String key) throws Exception {
        String fi = Input.getString(path);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(fi);
        String src = jsonObject.getString(key);
        return src;
        //返回传入路径和Key值对应的value值
    }
}
