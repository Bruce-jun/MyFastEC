package com.coderzj.min.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.coderzj.min.app.AccountManage;
import com.coderzj.min.app.IUserChecker;
import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.ui.launcher.ScrollLauncherTag;
import com.coderzj.min.util.storage.MinPreference;
import com.coderzj.min.util.timer.BaseTimerTask;
import com.coderzj.min.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 邹俊 on 2018/1/25.
 * 启动界面
 */

public class LauncherDalegate extends MinDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher)
    AppCompatTextView mTvTimer = null;

    private ILauncherFinish mILauncherFinish = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherFinish) {
            mILauncherFinish = (ILauncherFinish) activity;
        }
    }

    @OnClick(R2.id.tv_launcher)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private Timer mTimer = null;
    private int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.dalegete_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    private void checkIsShowScroll() {
        if (!MinPreference.getAppFlag(ScrollLauncherTag.IS_FIRST_IN_APP.name())) {
            startWithPop(new LauncherScrollDelegate());
        } else {
            //检查用户是否登录的App
            AccountManage.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    mILauncherFinish.onLaunchFinish(LauncherFinishTag.SIGN);
                }

                @Override
                public void onNotSignIn() {
                    mILauncherFinish.onLaunchFinish(LauncherFinishTag.NOT_SIGN);
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

}
