package com.coderzj.min.delegates.webview;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by 邹俊 on 2018/4/11.
 * 初始化WebView
 */
public class WebViewInit {

    @SuppressLint("SetJavaScriptEnabled")
    public WebView creatWebView(WebView webView){
        webView.setWebContentsDebuggingEnabled(true);
        //设置webView不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //设置webView不能垂直滚动
        webView.setVerticalScrollBarEnabled(false);
        //不允许截图
        webView.setDrawingCacheEnabled(false);
        //屏蔽长按事件
        webView.setLongClickable(false);
        //初始化Setting
        final WebSettings settings=webView.getSettings();
        final String ua=settings.getUserAgentString();
        settings.setUserAgentString(ua+"Min");//作为当前应用的标记
        settings.setJavaScriptEnabled(true);
        //隐藏缩放
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);



        return webView;
    }
}

