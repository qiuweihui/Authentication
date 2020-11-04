package com.cug.utils;

import cn.xjfme.encrypt.utils.sm2.SM2EncDecUtils;
import cn.xjfme.encrypt.utils.sm2.SM2KeyVO;

import java.io.IOException;

/**
 * @author qiuweihui
 * @create 2020-11-02 19:15
 */
public class GenerateSM2Key {
    public static void main(String[] args) throws IOException {
       // System.out.println("--产生SM2秘钥--:");
        SM2KeyVO sm2KeyVO = generateSM2Key();
       // System.out.println("公钥:" + sm2KeyVO.getPubHexInSoft());
        Output.wirteText(sm2KeyVO.getPubHexInSoft(),"D:\\TestData\\Vehicle\\pubkey.txt");
        //System.out.println("私钥:" + sm2KeyVO.getPriHexInSoft());
        Output.wirteText(sm2KeyVO.getPriHexInSoft(),"D:\\TestData\\Vehicle\\prikey.txt");
    }
    public static SM2KeyVO generateSM2Key() throws IOException {
        SM2KeyVO sm2KeyVO = SM2EncDecUtils.generateKeyPair();
        return sm2KeyVO;
    }
}
