package com.coderzj.min.net;

import android.content.Context;

import com.coderzj.min.net.callback.IError;
import com.coderzj.min.net.callback.IFailure;
import com.coderzj.min.net.callback.IRequest;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.ui.loader.LoadStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 邹俊 on 2018/1/10.
 * 建造者模式创建请求网络对象
 */

public class RestClientBuilder {
    private String mUrl = null;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IError mError = null;
    private IFailure mFailure = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoadStyle mStyle = null;
    private File mFile;

    private String mDir=null;
    private String mExtension=null;
    private String mName=null;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(
                MediaType.parse("application/json;charse=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder load(Context context, LoadStyle loadStyle) {
        this.mContext = context;
        this.mStyle = loadStyle;
        return this;
    }

    public final RestClientBuilder load(Context context) {
        this.mContext = context;
        this.mStyle = LoadStyle.BallPulseRiseIndicator;
        return this;
    }
    //dir表示下载后文件存放的目录
    public final RestClientBuilder dir(String dir) {
        this.mDir = dir;
        return this;
    }
    //文件的后缀名
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }
    //文件的名称
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mError, mFailure, mBody, mFile, mStyle, mContext,mDir,mExtension,mName);
    }

}
