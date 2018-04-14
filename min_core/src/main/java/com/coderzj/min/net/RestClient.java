package com.coderzj.min.net;

import android.content.Context;
import android.util.Log;

import com.coderzj.min.net.callback.IError;
import com.coderzj.min.net.callback.IFailure;
import com.coderzj.min.net.callback.IRequest;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.net.callback.ResquestCallBacks;
import com.coderzj.min.net.download.DownLoadHandle;
import com.coderzj.min.ui.loader.LoadStyle;
import com.coderzj.min.ui.loader.MinLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 邹俊 on 2018/1/8.
 * 网络请求的具体实现类
 */

public class RestClient {
    private static final String TAG = "RestClient";
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final File FILE;
    private final LoadStyle LOAD_STYLE;
    private final Context CONTEXT;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;



    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      File file,
                      LoadStyle style,
                      Context context,
                      String dir,
                      String extension,
                      String name) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.FILE = file;
        this.LOAD_STYLE = style;
        this.CONTEXT = context;
        this.DOWNLOAD_DIR=dir;
        this.EXTENSION=extension;
        this.NAME=name;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOAD_STYLE != null) {
            MinLoader.showLoading(CONTEXT, LoadStyle.BallBeatIndicator);
        }
        switch (method) {
            case GET:
                Log.d(TAG, "test: 执行..." + URL);
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(),requestBody);
                call=RestCreator.getRestService().upload(URL,body);

                break;
            case DOWNLOAD:

                break;
        }
        if (call != null) {
            call.enqueue(getCallback());
        }
    }

    private Callback<String> getCallback() {
        return new ResquestCallBacks(REQUEST, SUCCESS, ERROR, FAILURE, LOAD_STYLE);
    }

    public final void get() {
        Log.d(TAG, "get(): 执行啦");
        request(HttpMethod.GET);
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download(){
      new DownLoadHandle(
              URL,
              REQUEST,
              SUCCESS,
              ERROR,
              FAILURE,
              DOWNLOAD_DIR,
              EXTENSION,
              NAME).handleDownload();
    }
}
