package com.coderzj.min.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by 邹俊 on 2018/1/17.
 * 创建加载动画
 */

public class LoadCreator {
    private static final WeakHashMap<String, Indicator> LOAD_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context, String type) {
        final AVLoadingIndicatorView loadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOAD_MAP.get(type)==null) {
            //如不为空则直接使用
            final Indicator indicator = getIndicator(type);
            LOAD_MAP.put(type,indicator);
        }
        loadingIndicatorView.setIndicator(LOAD_MAP.get(type));
        return loadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null||name.isEmpty()) {
            return null;
        }
        StringBuilder drawbleClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawbleClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawbleClassName.append(name);
        try {
            final Class<?> drawbleClass = Class.forName(drawbleClassName.toString());
            return (Indicator) drawbleClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
