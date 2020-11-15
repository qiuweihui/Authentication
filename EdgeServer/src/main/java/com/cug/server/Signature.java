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

        String src = jsonToString("D:\\TestData\\EdgeServer\\SID_Time.json","SID_Time");
        String srcHex = Util.byteToHex(src.getBytes());
        //src是要签名的内容,将其转成Hex字符串

        //签名开始,用车的私钥签名Time和VID（即src）
        String prikey = jsonToString("D:\\TestData\\EdgeServer\\prikey.json","prikey");

        SM2SignVO sign = genSM2Signature(prikey.trim(), srcHex);
        JSONObject json = JSONUtil.parseObj(sign, true, true);
        Output.wirteText(String.valueOf(json),"D:\\TestData\\EdgeServer\\sign_server.json");
        //生成的签名会被发送给车

    }

}
