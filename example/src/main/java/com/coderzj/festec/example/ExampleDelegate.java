package com.coderzj.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.IError;
import com.coderzj.min.net.callback.IFailure;
import com.coderzj.min.net.callback.ISuccess;

/**
 * Created by 邹俊 on 2017/12/17.
 *
 */

public class ExampleDelegate extends MinDelegate {
    private static final String TAG="ExampleDelegate";
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//           test();
    }
    private void test(){
        Log.d(TAG, "test: 执行啦");
        RestClient.builder()
                .url("http://news.baidu.com")
                .load(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "test: 成功请求" );
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void error(int code, String msg) {

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
