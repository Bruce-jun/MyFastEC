package com.coderzj.min.ec.bottomtab.compass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.delegates.webview.WebViewDelegateImpl;
import com.coderzj.min.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 邹俊 on 2018/3/27.
 *  分类界面
 */

public class CompassDelegate extends ButtomItemdelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegeta_compass;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebViewDelegateImpl webViewDelegate=WebViewDelegateImpl.creat("index.html");
        webViewDelegate.setTopDelegate(this.getParentDelegete());
        loadRootFragment(R.id.fl_delegate_compass,webViewDelegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
