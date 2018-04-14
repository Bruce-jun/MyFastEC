package com.coderzj.min.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.coderzj.min.app.AccountManage;
import com.coderzj.min.app.IUserChecker;
import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ui.launcher.LauncherHolderCreator;
import com.coderzj.min.ui.launcher.ScrollLauncherTag;
import com.coderzj.min.util.storage.MinPreference;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/1/31.
 *  滚动的启动图
 */

public class LauncherScrollDelegate extends MinDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mBanner=null;
    private static final ArrayList<Integer> INTEGERS=new ArrayList<>();


    private ILauncherFinish mILauncherFinish = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherFinish) {
            mILauncherFinish = (ILauncherFinish) activity;
        }
    }

    private void initBanner(){
        if (INTEGERS.size()>4){
            INTEGERS.clear();
        }
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,
                        R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);//设置可以循环

    }

    @Override
    public Object setLayout() {
        mBanner=new ConvenientBanner<Integer>(getContext());
        return mBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position==INTEGERS.size()-1){
            //第一次进入
            MinPreference.setAppFlag(ScrollLauncherTag.IS_FIRST_IN_APP.name(),true);
            //检查用户是否登录的App
            AccountManage.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {

                }

                @Override
                public void onNotSignIn() {
                    mILauncherFinish.onLaunchFinish(LauncherFinishTag.NOT_SIGN);
                }
            });
        }


    }
}
