package com.coderzj.min.delegates.webview.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.delegates.webview.WebViewDelegate;
import com.coderzj.min.delegates.webview.WebViewDelegateImpl;

/**
 * Created by 邹俊 on 2018/4/11.
 * Web的路由
 */
public class Router {
    //采用单利模式

    private Router() {
    }

    private static class Holder {
        private final static Router ROUTER = new Router();
    }

    public static Router getInstence() {
        return Holder.ROUTER;
    }

    /**
     * @param delegate
     * @param url
     * @return 返回true表示由我们自己处理
     * 做路由截断，根据Url的类型来进行处理
     */
    public final boolean handleWebUrl(WebViewDelegate delegate, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callphone(delegate.getContext(), url);
            return true;
        }
        final MinDelegate topDelegate=delegate.getTopDelegate();
        final WebViewDelegateImpl webViewDelegate=WebViewDelegateImpl.creat(url);
        topDelegate.start(webViewDelegate);
        return true;
    }

    private void loadWebPage(WebView webView,String url){
       if (webView!=null){
           webView.loadUrl(url);
       }else {
           throw new NullPointerException("WebView Is NUll!");
       }
    }
    //加载本地assets目录下的文件
    private void loadlocalpage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    public final void loadpage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadlocalpage(webView,url);
        }
    }

    public final void loadpage(WebViewDelegate delegate,String url){
        loadpage(delegate.getWebView(),url);
    }


    private void callphone(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);

        ContextCompat.startActivity(context, intent, null);

    }
}

