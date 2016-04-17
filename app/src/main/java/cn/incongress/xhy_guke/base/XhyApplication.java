package cn.incongress.xhy_guke.base;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;
import com.umeng.socialize.PlatformConfig;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.incongress.xhy_guke.BuildConfig;
import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.utils.PicassoImageLoader;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 */
public class XhyApplication extends Application {
    /** 用户ID **/
    public static final String userId = "16004";
    /** 用户类型 **/
    public static final int userType = Constants.USER_TYPE_ORGANIZATION;

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        //微信分享配置 appId,appSecret
        PlatformConfig.setWeixin("wx69ad2ffcf273adb9", "423f40d0e4f88612ca59c3914bba78f7");

        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(R.color.theme_blue)
                .setTitleBarTextColor(R.color.white)
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(false)
                .setEnableCrop(false)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .setMutiSelectMaxSize(9)
            .build();

        //配置imageloader
        PicassoImageLoader imageloader = new PicassoImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageloader, theme)
                .setFunctionConfig(functionConfig)
        .build();
        GalleryFinal.init(coreConfig);
    }
}
