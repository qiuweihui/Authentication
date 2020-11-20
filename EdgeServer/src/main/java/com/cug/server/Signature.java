package com.cug.server;

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
 * 服务器端是用服务器私钥 ，对SID和事故发生的时间Time进行签名
 * 属于第5步的前提
 */


public class Signature {
    //生成签名
    public static SM2SignVO genSM2Signature(String priKey, String sourceData) throws Exception {
        SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexToByte(priKey), Util.hexToByte(sourceData));
        return sign;
    }
    public static String jsonToString(String path , String key) throws Exception {
        String fi = Input.getString(path);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(fi);
        String src = jsonObject.getString(key);
        return src;
        //返回传入路径和Key值对应的value值
    }
    public static void main(String[] args) throws Exception {

        String SID = jsonToString("D:\\TestData\\EdgeServer\\SID.json","SID");
        long timestamp=System.currentTimeMillis();
        String time = String.valueOf(timestamp);
        String SID_Time = SID + time;

        String srcHex = Util.byteToHex(SID_Time.getBytes());
        //src是要签名的内容,将其转成Hex字符串

        //签名开始,用车的私钥签名SID_Time

        String prikey = jsonToString("D:\\TestData\\EdgeServer\\prikey.json","prikey");
        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);
        JSONObject json = JSONUtil.parseObj(sign, true, true);
        String sm2_sign = json.getStr("sm2_sign");
        //我们只需要签名结果的sm2_sign对
        JSONObject signobj =new JSONObject();
        signobj.accumulate("sm2_sign",sm2_sign);
        signobj.accumulate("SID_Time",SID_Time);
        //将原文SID_Time和签名后的结果都放入signobj中
        Output.wirteText(String.valueOf(signobj),"D:\\TestData\\EdgeServer\\sign_server.json");

    }

}
