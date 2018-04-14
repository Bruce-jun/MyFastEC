package com.coderzj.min.ui.recycle;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by 邹俊 on 2018/3/29.
 *
 */

public class MultipleHolder extends BaseViewHolder {
    //简单工厂模式
    private MultipleHolder(View view) {
        super(view);
    }

    public static MultipleHolder creat(View view){
        return new MultipleHolder(view);
    }
}
