package com.coderzj.min.net;

import com.coderzj.min.app.ConfigType;
import com.coderzj.min.app.Min;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 邹俊 on 2018/1/8.
 * 创建网络请求
 */

public class RestCreator {

    public static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static final WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Min.getConfiguration(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkhttpHolder {
        private static final int TIME_OUT = 60;
        private static final ArrayList<Interceptor> INTERCEPTORS
                = Min.getConfiguration(ConfigType.INTERCEPTOR.name());
        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private  static OkHttpClient.Builder addInterceptor(ArrayList<Interceptor> INTERCEPTORS){
            for (Interceptor interceptor: INTERCEPTORS) {
                BUILDER.addInterceptor(interceptor);
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT
                =BUILDER
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                .addInterceptor()  //在respones时候调用
//                .addNetworkInterceptor()   //添加拦截器   在request和respones时调用
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
