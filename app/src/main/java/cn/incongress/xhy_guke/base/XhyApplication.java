package cn.incongress.xhy_guke.base;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 */
public class XhyApplication extends Application {
    /** 用户ID **/
    public static final String userId = "16004";
    /** 用户类型 **/
    public static final int userType = Constants.USER_TYPE_ORGANIZATION;

    @Override
    public void onCreate() {
        super.onCreate();

        //微信分享配置 appId,appSecret
        PlatformConfig.setWeixin("wx69ad2ffcf273adb9", "423f40d0e4f88612ca59c3914bba78f7");
    }
}
