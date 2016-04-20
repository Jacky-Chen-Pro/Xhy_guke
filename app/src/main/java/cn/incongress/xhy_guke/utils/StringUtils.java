package cn.incongress.xhy_guke.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public final class StringUtils {

    /**
     * 正则剔除左右空白字符， 空白字符：[ \t\n\x0B\f\r]
     */
    public final static String TRIM_REGULAT_EXPRESSION = "^(\\s)*|(\\s)*$";

    public final static String EMPTY_STRING = "";

    public final static String[] EMPTY_STRING_ARRAY = new String[]{};

    public final static boolean isEmpty(String... s) {
        boolean m = true;
        for (String i : s) {
            m = m && isEmpty(i);
            if (!m) break;
        }
        return m;
    }

    public final static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public final static boolean isNotEmpty(String... s) {
        boolean m = true;
        for (String i : s) {
            m = m && isNotEmpty(i);
            if (!m) break;
        }
        return m;
    }

    public final static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public final static boolean isEmpty(Object o) {
        return o == null || isEmpty(String.valueOf(o));
    }

    public final static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public final static String getString(Object o, String defaultValue) {
        return isEmpty(o) ? defaultValue : String.valueOf(o);
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isPassword(String password) {
        Pattern p = Pattern.compile("^[A-Za-z0-9\\-]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

}
