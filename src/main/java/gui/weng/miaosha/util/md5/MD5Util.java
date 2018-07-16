package gui.weng.miaosha.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt="1a2b3c4d";

    public  static String mds(String src){
        return DigestUtils.md5Hex(src);
    }

    /**
     * 将用户输入的密码做第一次MD5
     * @param inputPass
     * @return
     */
    public static String inputPass2FromPass(String inputPass){
        String str = salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return mds(str);
    }

    /**
     * 将form的密码到DB中密码
     * 数据库中将是存放这MD5码
     * @param inputPass
     * @return
     */
    public static String fromPass2DBPass(String inputPass,String salt){
        String str = salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return mds(str);
    }

    public static String inputPass2DBPass(String input,String saltDb){
        String fromPass = inputPass2FromPass(input);
        String dbPass = fromPass2DBPass(fromPass, saltDb);
        return dbPass;
    }

    public static void main(String[] args) {
        String dbPass = inputPass2DBPass("33492526", "1a2b3c4d");
        System.out.println(dbPass);
    }
}
