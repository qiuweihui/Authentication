package SM2;

import com.cug.SM2.SM2;
import com.cug.SM2.SM2KeyPair;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @author qiuweihui
 * @create 2020-10-27 22:22
 */
public class TestSM2 {
    public static void main(String[] args) {
        SM2 x = new SM2();
        SM2KeyPair keys = x.generateKeyPair();
        ECPoint pubKey = keys.getPublicKey();
        BigInteger privKey = keys.getPrivateKey();
        byte[] data = x.encrypt("HelloSM2", pubKey);
        System.out.println("encrypt: " + data);
        String origin = x.decrypt(data, privKey);
        System.out.println("decrypt: " + origin);
    }
}
