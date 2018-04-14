package com.coderzj.festec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.coderzj.min.activities.ProxyActivity;
import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.bottomtab.Bottomdalegate;
import com.coderzj.min.ec.launcher.ILauncherFinish;
import com.coderzj.min.ec.launcher.LauncherDalegate;
import com.coderzj.min.ec.launcher.LauncherFinishTag;
import com.coderzj.min.ec.sign.ISignListener;
import com.coderzj.min.ec.sign.SignInDelegete;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherFinish{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public MinDelegate setRootDelegate() {

        return new LauncherDalegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"Login Success！",Toast.LENGTH_SHORT).show();
        startWithPop(new Bottomdalegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"SignUp Success！",Toast.LENGTH_SHORT).show();
        start(new SignInDelegete());
    }

    @Override
    public void onLaunchFinish(LauncherFinishTag tag) {
        switch (tag){
            case SIGN:
                Toast.makeText(this,"已经登录",Toast.LENGTH_SHORT).show();
                startWithPop(new Bottomdalegate());
                break;
            case NOT_SIGN:
                Toast.makeText(this,"没有登录",Toast.LENGTH_SHORT).show();
                start(new SignInDelegete());
                break;
        }
    }
}
