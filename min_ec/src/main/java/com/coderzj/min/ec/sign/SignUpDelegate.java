package com.coderzj.min.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
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
 * Created by 邹俊 on 2018/2/1.
 * 注册模块
 */

public class SignUpDelegate extends MinDelegate {
    private static final String TAG="SignUpDelegate";
    //绑定控件
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_repassword)
    TextInputEditText mRePassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener= (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){
            String name=mName.getText().toString();
            String phone=mPhone.getText().toString();
            String email=mEmail.getText().toString();
            String password=mPassword.getText().toString();
            Log.e(TAG, "请求开始");
            RestClient.builder()
                    .url("http://192.168.1.103:8080/RestServer/data/user_profile.json")
                    .params("name",name)
                    .params("phone",phone)
                    .params("email",email)
                    .params("password",password)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e(TAG, "onSuccess: "+"success!");
                          SignUpHandler.onSignUp(response,mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.e(TAG, "Failure！");
                        }
                    })
                    .build()
                    .post();
        }
    }
    @OnClick(R2.id.tv_sign_up)
    void onClick() {
        start(new SignInDelegete());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phoneNum = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String repassword = mRePassword.getText().toString();
        boolean isPass =true;

        //判断用户名输入
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        //Patterns.EMAIL_ADDRESS.matcher(email).matches()  Android提供的检验的类
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phoneNum.isEmpty() || phoneNum.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() <6) {
            mPassword.setError("请至少填写6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (repassword.isEmpty() || repassword.length() < 6 || !repassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

      return isPass;

    }
}
