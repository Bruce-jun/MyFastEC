package com.coderzj.min.delegates.webview;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.coderzj.min.delegates.webview.event.Event;
import com.coderzj.min.delegates.webview.event.EventManage;

/**
 * Created by 邹俊 on 2018/4/11.
 */
public class MinWebInterface {

    final WebViewDelegate  mDelegate;

    private MinWebInterface(WebViewDelegate delegate) {
        mDelegate = delegate;
    }

    static MinWebInterface creat(WebViewDelegate delegate){
        return  new MinWebInterface(delegate);
    }
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action= JSON.parseObject(params).getString("action");
        final Event event= EventManage.getInstance().createEvent(action);
        if (event!=null){
            event.setAction(action);
            event.setWebViewDelegate(mDelegate);
            event.setContext(mDelegate.getContext());
            event.setUrl(mDelegate.getUrl());
            return event.execute(params);
        }

        return null;
    }

}
