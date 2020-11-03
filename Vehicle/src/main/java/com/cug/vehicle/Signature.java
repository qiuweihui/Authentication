package com.cug.vehicle;

import cn.xjfme.encrypt.utils.Util;
import cn.xjfme.encrypt.utils.sm2.SM2SignVO;
import cn.xjfme.encrypt.utils.sm2.SM2SignVerUtils;
import com.cug.utils.Input;
import com.cug.utils.Output;

/**
 * @author qiuweihui
 * @create 2020-10-27 21:22
 *
 * 车辆端签名
 * 用车的私钥对车的VID和事故发生的时间进行签名
 */

public class Signature {

    public static void main(String[] args) throws Exception {

        //--测试SM2签名--
        String src = Input.getString("D:\\TestData\\Vehicle\\VID_Time.txt");
        String srcHex = Util.byteToHex(src.getBytes());  //src是要签名的内容,将其转成Hex字符串

        //签名开始,用车的私钥签名Time和VID（即src）
        String prikey = Input.getString("D:\\TestData\\Vehicle\\prikey.txt");
        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);

        Output.wirteText(String.valueOf(sign),"D:\\TestData\\Vehicle\\sign_vehicle");

        //验签，用车公钥验签
        String pubkey = Input.getString("D:\\TestData\\Vehicle\\pubkey.txt");
        boolean b = verifySM2Signature(pubkey.trim(), srcHex, sign.getSm2_signForSoft());
        System.out.println("软件加密方式验签结果:" + b);
    }
    //生成签名
    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }
    //公钥验签,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }
}
