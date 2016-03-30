package cn.incongress.xhy_guke.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jacky on 2016/1/15.
 * 弹出工具类
 */
public class ToastUtils {
    public static void showShorToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
