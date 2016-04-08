package cn.incongress.xhy_guke.base;

/**
 * Created by Jacky on 2016/3/25.
 */
public class Constants {
    /** 页码长度 **/
    public static final String PAGE_SIZE = "12";
    /** 默认编码方式 **/
    public static final String ENCODDING_UTF8 = "utf-8";
    /** 客户端类型 1:ios ; 2:android **/
    public static final String CLIENT_TYPE = "2";

    /** 课件详情中的附件类型，见名知意 **/
    public static final int DATA_TYPE_PPT = 1;
    public static final int DATA_TYPE_WORD = 2;
    public static final int DATA_TYPE_PDF = 3;

    /** 用户类型 **/
    public static final int USER_TYPE_NORMAL = 0;//普通用户
    public static final int USER_TYPE_SERIAL_VIP = 1; //大V
    public static final int USER_TYPE_MEDIA_VIP = 2;//中V
    public static final int USER_TYPE_NORMAL_CERTIFY = 3; //已认证用户
    public static final int USER_TYPE_ORGANIZATION = 4; //组织

}
