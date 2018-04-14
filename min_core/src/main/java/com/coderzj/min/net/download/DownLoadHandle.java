package com.coderzj.min.net.download;

import android.os.AsyncTask;

import com.coderzj.min.net.RestCreator;
import com.coderzj.min.net.callback.IError;
import com.coderzj.min.net.callback.IFailure;
import com.coderzj.min.net.callback.IRequest;
import com.coderzj.min.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 邹俊 on 2018/1/23.
 * 下载的处理类
 */

public class DownLoadHandle {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownLoadHandle(String url,
                          IRequest request,
                          ISuccess success,
                          IError error,
                          IFailure failure,
                          String download_dir,
                          String extension,
                          String name) {
        URL = url;
        REQUEST = request;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();//开始下载
        }
        RestCreator.getRestService().download(URL, PARAMS).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final ResponseBody body=response.body();
                            final DownLoadTask task=new DownLoadTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,body,NAME);
                            //一定要判断，否则文件下载不全
                            if (task.isCancelled()){
                                if (REQUEST!=null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null){
                                ERROR.error(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
