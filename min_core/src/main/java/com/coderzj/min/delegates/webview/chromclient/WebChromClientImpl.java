package com.coderzj.min.delegates.webview.chromclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public class WebChromClientImpl extends WebChromeClient{

    //可以拦截用来弹出自己的对话框
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

}
