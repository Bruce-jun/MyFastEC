package com.coderzj.min.delegates.webview.client;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coderzj.min.delegates.webview.WebViewDelegate;
import com.coderzj.min.delegates.webview.route.Router;
import com.coderzj.min.ui.loader.MinLoader;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public class WebViewClientImpl extends WebViewClient {

    private WebViewDelegate mWebViewDelegate;

    private IPageLoadListener mIPageLoadListener=null;

    public void setIPageLoadListener(IPageLoadListener listener){
        mIPageLoadListener=listener;
    }


    public WebViewClientImpl(WebViewDelegate webViewDelegate) {
        mWebViewDelegate = webViewDelegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //需要在这里做路由的截断和处理
        Log.d("WebViewClientImpl", "" + url);
        return Router.getInstence().handleWebUrl(mWebViewDelegate, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        MinLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadEnd();
        }
        MinLoader.stopLoading();
    }
}
