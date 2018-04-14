package com.coderzj.min.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by 邹俊 on 2018/1/31.
 *
 */

public class LauncherHolder implements Holder<Integer> {
    //兼容包下的IageView
    private AppCompatImageView mImageView=null;
    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
