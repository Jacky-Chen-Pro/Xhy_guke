package cn.incongress.xhy_guke.base;

import android.os.Environment;

/**
 * Created by Jacky on 2016/3/25.
 */
public class Constants {
    /** 项目名称 **/
    public static final String PROJECT_NAME = "lnyx";
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

    /** 最大下载数量 **/
    public static final int MAX_TASK = 5;
    /** 最多可以选择的图片数量 **/
    public static final int MAX_CHOOSE_PHOTO = 9;

    /** 添加关注 **/
    public static final String FOCUS_ADD = "1";
    /** 取消关注 **/
    public static final String FOCUS_CANCEL = "0";

    /** 添加收藏 **/
    public static final String COLLECT_ADD = "1";
    /** 取消收藏 **/
    public static final String COLLECT_CANCEL = "0";

    /** 个人信息相关的字段 **/
    public static String USER_USER_ID = "userId";
    public static String USER_TRUE_NAME = "trueName";
    public static String USER_PIC = "userPic";
    public static String USER_NICKNAME = "nickname";
    public static String USER_SEX = "sex";
    public static String USER_MOBILE = "mobilePhone";
    public static String USER_EMAIL = "email";
    public static String USER_KESHI = "keshi";
    public static String USER_ZHICHENG = "zhicheng";
    public static String USER_PROVINCE_ID = "province";
    public static String USER_PROVINCE_NAME = "provinceName";
    public static String USER_CITY_NAME = "cityName";
    public static String USER_CITY_ID = "city";
    public static String USER_HOSPITAL_ID = "hospital";
    public static String USER_HOSPITAL_NAME = "hospitalName";
    public static String USER_UNIV = "univ";
    public static String USER_UNIV_ID = "univsId";
    public static String USER_UNIV_YEAR = "univYear";
    public static String USER_REMARK = "remark";
    public static String USER_TYPE = "type";//1专家 2普通用户
}
