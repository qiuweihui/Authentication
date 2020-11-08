package com.cug.vehicle;

import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2SignVO;
import cn.xjfme.encrypt.utils.sm2.SM2SignVerUtils;
import com.alibaba.fastjson.JSONObject;
import com.cug.utils.Input;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:25
 * 小车端验签，使用服务器公钥对服务器发过来的签名进行验证，验证通过后才会发生加密数据给服务器
 * 发生在7、8步之间
 */
public class SignVerify {
    //公钥验签,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

    public static void main(String[] args) throws Exception {

        //--SM2签名--
        String src = Input.getString("D:\\TestData\\Vehicle\\src_sign_server.json");
        String srcHex = Util.byteToHex(src.getBytes());  //读入原文，即要签名的原内容,将其转成Hex字符串

        String pubkey = Input.getString("D:\\TestData\\Vehicle\\pubkey_server.json");//直接读入服务器公钥

        String sign = Input.getString("D:\\TestData\\Vehicle\\sign_server.json");//读入签名内容，只取其中sm2_sign部分
        JSONObject jsonObject = JSONObject.parseObject(sign);
        String sm2_sign = jsonObject.getString("sm2_sign");

        //验签，用服务器的公钥验签
        boolean b = verifySM2Signature(pubkey.trim(), srcHex, sm2_sign);//公钥，原文，签名内容
        System.out.println("验签结果:" + b);

    }
}
