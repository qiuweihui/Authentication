package SM3;

import static cn.xjfme.encrypt.test.SecurityTestAll.generateSM3HASH;

/**
 * @author qiuweihui
 * @create 2020-10-27 22:21
 */
public class TestSm3 {

    public static void main(String[] args) {
        String src = "I Love You";
        System.out.println("--SM3摘要测试--");
        String s = generateSM3HASH(src);
        System.out.println("hash:"+s);
        System.out.println("--SM3摘要结束--");

    }
}


