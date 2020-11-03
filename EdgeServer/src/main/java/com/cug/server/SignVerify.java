package com.cug.server;


import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2SignVO;
import cn.xjfme.encrypt.utils.sm2.SM2SignVerUtils;
import com.cug.utils.Input;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:41
 * 服务器端验签
 * 用车的公钥验签
 */
public class SignVerify {
    public static void main(String[] args) throws Exception {

        //--测试SM2签名--
        String src = Input.getString("D:\\TestData\\EdgeServer\\src_sign_vehicle.txt");
        String srcHex = Util.byteToHex(src.getBytes());  //读入原文，即要签名的原内容,将其转成Hex字符串

        String pubkey = Input.getString("D:\\TestData\\EdgeServer\\pubkey_vehicle.txt");
        String pubkeyHex = Util.byteToHex(pubkey.getBytes());

        String sign = Input.getString("D:\\TestData\\EdgeServer\\sign_vehicle.txt");
        String signHex = Util.byteToHex(sign.getBytes());

        //验签，用车公钥验签
        boolean b = verifySM2Signature(pubkeyHex, srcHex, signHex);//公钥，原文，签名内容
        System.out.println("软件加密方式验签结果:" + b);
    }

    //公钥验签,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }

}
