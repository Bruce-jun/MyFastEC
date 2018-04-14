package com.coderzj.min.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderzj.min.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by 邹俊 on 2017/12/15 .
 * 项目基础的 Fragment （ Delegate代表 )
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    //由子类设置其布局
    public abstract Object setLayout();
    private Unbinder mUnbinder = null;
    //绑定视图
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                               @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            //增加程序的健壮性
            throw new ClassCastException("setLayout() type must be int or view!");
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);//
        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}