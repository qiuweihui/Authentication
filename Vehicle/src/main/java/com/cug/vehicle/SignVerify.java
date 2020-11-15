package com.cug.vehicle;

import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2SignVO;
import cn.xjfme.encrypt.utils.sm2.SM2SignVerUtils;
import com.cug.utils.Input;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:25
 * 小车端验签，使用服务器公钥对服务器发过来的签名进行验证，验证通过后才会发生加密数据给服务器
 * 发生在7、8步之间
 */
public class SignVerify {

    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

    public static String jsonToString(String path , String key) throws Exception {
        String fi = Input.getString(path);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(fi);
        String src = jsonObject.getString(key);
        return src;
        //返回传入路径和Key值对应的value值
    }

    public static void main(String[] args) throws Exception {


        String src = jsonToString("D:\\TestData\\Vehicle\\response_receive.json","SID_Time");
        String srcHex = Util.byteToHex(src.getBytes());
        //读入签名的原内容,将其转成Hex字符串

        String pubkey = jsonToString("D:\\TestData\\Vehicle\\pubkey_server.json","pubkey");
        //读如服务器公钥
        String sm2_sign = jsonToString("D:\\TestData\\Vehicle\\response_receive.json","sm2_sign");
        //读入签名内容，只取其中sm2_sign部分

        //验签，用服务器的公钥验签
        boolean b = verifySM2Signature(pubkey.trim(), srcHex, sm2_sign);
        //公钥，原文，签名内容
        System.out.println(b);

    }
}
