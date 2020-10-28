package com.cug.SM2;

import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @author qiuweihui
 * @create 2020-10-28 9:56
 */
public class SM2KeyPair {


    private final ECPoint publicKey;
    private final BigInteger privateKey;

    SM2KeyPair(ECPoint publicKey, BigInteger privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }
}
