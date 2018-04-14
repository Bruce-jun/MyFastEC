package com.coderzj.min.delegates.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.coderzj.min.app.ConfigType;
import com.coderzj.min.app.Min;
import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.delegates.webview.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public abstract class WebViewDelegate extends MinDelegate implements IWebViewInitializer{

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEBVIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailabel = false;//确保WebView已经初始化完成，我们获取到的是可用的
    private MinDelegate mMinDelegate=null;

    public WebViewDelegate() {
    }

    public abstract IWebViewInitializer setInitialzer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    /**
     * 初始化WebView
     */
    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            //为空，开始创建
            final IWebViewInitializer webViewInitializer = setInitialzer();
            if (webViewInitializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEBVIEW_QUEUE);
                mWebView=webViewWeakReference.get();
                mWebView=webViewInitializer.initWebView(mWebView);
                mWebView.setWebViewClient(webViewInitializer.initWebViewClient());
                mWebView.setWebChromeClient(webViewInitializer.initWebChromeClient());
                final String name= (String) Min.getConfigurations().get(ConfigType.JAVASCRIPT_NAME.name());
                mWebView.addJavascriptInterface(MinWebInterface.creat(this),name);
                mIsWebViewAbailabel=true;
            }else {
                throw new NullPointerException("Initializer is null！");
            }
        }
    }

    public void setTopDelegate(MinDelegate delegate){
      mMinDelegate=delegate;
    }

    public  MinDelegate getTopDelegate(){
        if (mMinDelegate!=null){
            return mMinDelegate;
        }
       return this;
    }
    /**
     * @return WebViewClientImpl
     */
    public WebView getWebView() {
        if (mWebView==null) {
            throw new NullPointerException("WebViewClientImpl is null！");
        }
        return mIsWebViewAbailabel? mWebView:null;
    }

    /**
     * @return UR
     */
    public String getUrl() {
        if (mUrl==null){
            throw new NullPointerException("Url is null！");
        }
        return mUrl ;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailabel=false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
