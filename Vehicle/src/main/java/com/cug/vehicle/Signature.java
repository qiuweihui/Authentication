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
    //JsonTOString
    public static String jsonToString(String path , String key) throws Exception {
        String fi = Input.getString(path);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(fi);
        String src = jsonObject.getString(key);
        return src;
        //返回传入路径和Key值对应的value值
    }
    public static void main(String[] args) throws Exception {

        String VID = jsonToString("D:\\TestData\\Vehicle\\VID.json","VID");
        long timestamp=System.currentTimeMillis();
        String time = String.valueOf(timestamp);
        String VID_Time = VID + time;
        String srcHex = Util.byteToHex(VID_Time.getBytes());
        //src是要签名的内容,将其转成Hex字符串

        //签名开始,用车的私钥签名Time和VID（即src）
        String prikey = jsonToString("D:\\TestData\\Vehicle\\prikey.json","prikey");

        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);
        JSONObject json = JSONUtil.parseObj(sign);
        String sm2_sign = json.getStr("sm2_sign");
        //我们只需要签名结果的sm2_sign对
        JSONObject signobj =new JSONObject();
        signobj.accumulate("sm2_sign",sm2_sign);
        signobj.accumulate("VID_Time",VID_Time);
        //将原文VID_Time和签名后的结果都放入signobj中
        Output.wirteText(String.valueOf(signobj),"D:\\TestData\\Vehicle\\sign_vehicle.json");

    }

}
