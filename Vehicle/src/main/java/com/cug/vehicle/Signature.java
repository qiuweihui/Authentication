package com.cug.vehicle;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
 * 小车端用车的私钥对车的VID和事故发生的时间Time进行签名
 * 属于第2步前提
 */

public class Signature {

    //生成签名
    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }
    public static void main(String[] args) throws Exception {

        //--测试SM2签名--
        String src = Input.getString("D:\\TestData\\Vehicle\\VID_Time.json");
        String srcHex = Util.byteToHex(src.getBytes());
        //src是要签名的内容,将其转成Hex字符串

        //签名开始,用车的私钥签名Time和VID（即src）
        String prikey = Input.getString("D:\\TestData\\Vehicle\\prikey.json");
        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);
        JSONObject json = JSONUtil.parseObj(sign, true, true);
       Output.wirteText(String.valueOf(json),"D:\\TestData\\Vehicle\\sign_vehicle.json");

    }

}
