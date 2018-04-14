package com.coderzj.min.net.callback;

import android.os.Handler;
import android.util.Log;

import com.coderzj.min.ui.loader.LoadStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 邹俊 on 2018/1/10.
 * 请求回调
 */

public class ResquestCallBacks implements Callback<String> {
    private static final String TAG="ResquestCallBacks";
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoadStyle STYTLE;
    private Handler mHandler=new Handler();

    public ResquestCallBacks(IRequest request,
                             ISuccess success,
                             IError error,
                             IFailure failure,
                             LoadStyle style) {
        REQUEST = request;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        this.STYTLE=style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null)
                    SUCCESS.onSuccess(response.body());
            }
        }else {
            if (ERROR!=null){
                ERROR.error(response.code(),response.message());
            }
        }

    }
    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
