package com.cug.server;

import com.alibaba.fastjson.JSONObject;
import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2SignVO;
import cn.xjfme.encrypt.utils.sm2.SM2SignVerUtils;
import com.cug.utils.Input;
/**
 * @author qiuweihui
 * @create 2020-10-27 21:41
 * 服务器端用小车的公钥验证和发生的签名是否匹配，通过后才会进行第5步
 * 步骤4、5之间
 */
public class SignVerify {

    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

    public static boolean signVerify(String[] args) throws Exception {

        //签名
        String src = Input.getString("D:\\TestData\\EdgeServer\\VID_Time.json");
        String srcHex = Util.byteToHex(src.getBytes());
        //读入签名的原内容,将其转成Hex字符串
        String pubkey = Input.getString("D:\\TestData\\EdgeServer\\pubkey_vehicle.json");
        //读入公钥
        String sign = Input.getString("D:\\TestData\\EdgeServer\\sign_vehicle.json");
        //读入签名内容，只取其中sm2_sign部分
        JSONObject jsonObject = JSONObject.parseObject(sign);
        String sm2_sign = jsonObject.getString("sm2_sign");

        //验签，用车的公钥验签；（公钥，原文，签名内容）
        return verifySM2Signature(pubkey.trim(), srcHex, sm2_sign);

    }

}
