package cn.hdu.fragmentTax.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    /**
     * MD5方法
     *
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text + key);
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5){
        //根据传入的密钥进行验证
        try {
            String md5Text = md5(text, key);
            if(md5Text.equalsIgnoreCase(md5))
            {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        String encodeStr = md5("123456", "mike");
        System.out.println(verify("123456", "mike", "93e57a20fdb000ad8d3d60640981bef0"));
    }
}