package com.coderzj.min.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.coderzj.min.R;
import com.coderzj.min.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/1/18.
 *  展示加载界面
 */

public class MinLoader {
    private final static int LOADER_SCALE=8;
    private final static int LOADER_OFFSET_SCALE=10;

    private static final ArrayList<AppCompatDialog> mDialogs=new ArrayList<>();

    private static final String LOAD_DEFAULT=LoadStyle.BallClipRotateMultipleIndicator.name();

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView indicatorView=LoadCreator.create(context,type);
        dialog.setContentView(indicatorView);

        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeight= DimenUtil.getScreenHeight();

        final Window dialogWindow=dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams params=dialogWindow.getAttributes();
            params.width=deviceWidth/LOADER_SCALE;
            params.height=deviceHeight/LOADER_SCALE;
            params.height=params.height+deviceHeight/LOADER_OFFSET_SCALE;//偏移量
            params.gravity= Gravity.CENTER;//居中
        }
        mDialogs.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,LOAD_DEFAULT);
    }

    public static void showLoading(Context context,Enum<LoadStyle> type){
        showLoading(context,type.name());
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog:mDialogs) {
            if (dialog!=null){
                dialog.cancel();
            }
        }
    }
}
