package com.coderzj.min.delegates.bottom;

/**
 * Created by 邹俊 on 2018/3/26.
 * 保存每个Tab的信息
 */

public class ButtomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public ButtomTabBean(CharSequence icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
