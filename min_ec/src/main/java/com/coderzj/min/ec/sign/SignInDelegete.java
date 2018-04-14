package com.coderzj.min.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.IFailure;
import com.coderzj.min.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 邹俊 on 2018/2/27.
 * 登录模块
 */

public class SignInDelegete extends MinDelegate {
    @BindView(R2.id.edit_sign_in_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;
    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener= (ISignListener) activity;
        }
    }


    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            String phone=mPhone.getText().toString();
            String password=mPassword.getText().toString();
            RestClient.builder()
                    .url("http://192.168.43.126:8080/RestServer/data/user_profile.json")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignUpHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {

                        }
                    })
                    .build()
                    .get();
        }
    }

    @OnClick(R2.id.icon_signin_wechat)
    void onClickWeiChat() {

    }

    @OnClick(R2.id.tv_sign_in)
    void onClick() {
        start(new SignUpDelegate());
    }
     private  Handler mHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_signin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {

        final String phoneNum = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;

        if (phoneNum.isEmpty() || phoneNum.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请至少填写6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;

    }
}
