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
    public static void main(String[] args) throws Exception {

        //--测试SM2签名--
        String src = Input.getString("D:\\TestData\\EdgeServer\\SID_Time.json");
        String srcHex = Util.byteToHex(src.getBytes());  //src是要签名的内容,将其转成Hex字符串

        //签名开始,用服务器的私钥签名Time和SID（即src）
        String prikey = Input.getString("D:\\TestData\\EdgeServer\\prikey.json");
        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);
        JSONObject json = JSONUtil.parseObj(sign, true, true);
        Output.wirteText(String.valueOf(json),"D:\\TestData\\EdgeServer\\sign_self.json");//生成的签名会被发送给车
        // System.out.println("签名生成完成");

    }

}
