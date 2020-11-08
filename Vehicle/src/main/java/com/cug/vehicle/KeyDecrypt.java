package com.cug.vehicle;

import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2EncDecUtils;
import com.alibaba.fastjson.JSONObject;
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


        String jsonkey = Input.getString("D:\\TestData\\Vehicle\\key.json");//读入小车的SM2私钥
        JSONObject jsonObject = JSONObject.parseObject(jsonkey);
        String prikey = jsonObject.getString("prikey");

        String encrypt = Input.getString("D:\\TestData\\EdgeServer\\key_encrypt.json");//读入服务器发来的加密内容

        JSONObject encryptObject = JSONObject.parseObject(encrypt);
        String encryptInfo = encryptObject.getString("encrypt");
        String decryptInfo = SM2Dec(prikey,encryptInfo);        //解密出SM4Key和服务器公钥，分别存储，直接存为字符串即可
        Output.wirteText(decryptInfo.substring(0,130),"D:\\TestData\\Vehicle\\pubkey_server.json");
        Output.wirteText(decryptInfo.substring(130),"D:\\TestData\\Vehicle\\sm4key.json");

    }
    //私钥解密
    public static String SM2Dec(String priKey, String encryptedData) throws IOException {
        //填充04
        encryptedData="04"+encryptedData;
        byte[] decrypt = SM2EncDecUtils.decrypt(Util.hexStringToBytes(priKey), Util.hexStringToBytes(encryptedData));
        return new String(decrypt);
    }
}
