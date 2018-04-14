package com.coderzj.min.delegates.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coderzj.min.delegates.webview.chromclient.WebChromClientImpl;
import com.coderzj.min.delegates.webview.client.WebViewClientImpl;
import com.coderzj.min.delegates.webview.route.RouteKeys;
import com.coderzj.min.delegates.webview.route.Router;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public class WebViewDelegateImpl extends WebViewDelegate{

    public static WebViewDelegateImpl creat(String url){
       final  Bundle args=new Bundle();
       args.putString(RouteKeys.URL.name(),url);
       final WebViewDelegateImpl delegate=new WebViewDelegateImpl();
       delegate.setArguments(args);
       return delegate;
    }

    @Override
    public IWebViewInitializer setInitialzer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //永原生的方法模拟Web跳转
        if (getUrl()!=null){
            Router.getInstence().loadpage(this,getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInit().creatWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client=new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromClientImpl();
    }
}
