package cn.incongress.xhy_guke.base;

import android.app.Application;

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

    }
}
