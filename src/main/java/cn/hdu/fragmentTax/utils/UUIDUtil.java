package cn.hdu.fragmentTax.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    public static String BASECHAR = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public static String UString(int length) {
        return UUID.randomUUID().toString().substring(length);
    }

    // 输入6位随机数字字符
    public static String RInt(int length) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }

    // 从base里随机取值
    public static String RBase(String base, int length) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(36);
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer integer = Integer.valueOf(RInt(8));
        System.out.println(integer);
    }
}
