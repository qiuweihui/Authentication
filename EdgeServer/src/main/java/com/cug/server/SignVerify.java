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
        String src = Input.getString("D:\\TestData\\Vehicle\\VID_Time.txt");
        String srcHex = Util.byteToHex(src.getBytes());  //src是要签名的内容,将其转成Hex字符串

       /* //签名测试开始,用车的私钥签名Time和VID（即src）
        String src1 = Util.byteToHex(src.getBytes());
        String prikey = Input.getString("D:\\TestData\\Vehicle\\prikey.txt");
        SM2SignVO sign = genSM2Signature(prikey.trim(), src1);
        //System.out.println(sign);*/

        //验签，用车私钥验签
        String pubkey = Input.getString("D:\\TestData\\Vehicle\\pubkey.txt");
        String sign = Input.getString("D:\\TestData\\Vehicle\\sign_vehicle");
        boolean b = verifySM2Signature(pubkey.trim(), srcHex, sign);//公钥，原文，签名后内容
        System.out.println("验签结果:" + b);
    }
  /*  public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }*/
        //公钥验签,参数二:原串必须是hex!!!!因为是直接用于计算签名的,可能是SM3串,也可能是普通串转Hex
    public static boolean verifySM2Signature(String pubKey, String sourceData, String hardSign) {
        SM2SignVO verify = SM2SignVerUtils.VerifySignSM2(Util.hexStringToBytes(pubKey), Util.hexToByte(sourceData), Util.hexToByte(hardSign));
        return verify.isVerify();
    }
}
