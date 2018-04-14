package com.coderzj.min.delegates.webview.event;

import android.content.Context;
import android.webkit.WebView;

import com.coderzj.min.delegates.webview.WebViewDelegate;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public abstract  class Event implements IEvent {
    private Context  mContext=null;
    private String mAction=null;
    private WebViewDelegate mWebViewDelegate=null;
    private String mUrl;
    private WebView mWebView=null;

    public WebView getWebView() {
        mWebView=mWebViewDelegate.getWebView();
        return mWebView;
    }


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public WebViewDelegate getWebViewDelegate() {
        return mWebViewDelegate;
    }

    public void setWebViewDelegate(WebViewDelegate minDelegate) {
        mWebViewDelegate = minDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
