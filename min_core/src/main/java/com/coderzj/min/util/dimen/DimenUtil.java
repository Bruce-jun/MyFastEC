package com.coderzj.min.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.coderzj.min.app.Min;

/**
 * Created by 邹俊 on 2018/1/18.
 * 获取屏幕宽高
 */

public class DimenUtil {

    /**
     * @return 屏幕的宽
     */
    public static int getScreenWidth(){
        final Resources resources=Min.getApplicationContext().getResources();
        final DisplayMetrics metrics=resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * @return 屏幕的高
     */
    public static int getScreenHeight(){
        final Resources resources=Min.getApplicationContext().getResources();
        final DisplayMetrics metrics=resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
