package com.coderzj.min.net.callback;

/**
 * Created by 邹俊 on 2018/1/10.
 * 请求的回调
 */

public interface IRequest {
    void onRequestStart();
    void onRequestEnd();
}
