package com.coderzj.min.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.coderzj.min.R;
import com.coderzj.min.delegates.MinDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 邹俊 on 2017/12/15.
 * (Proxy代理)
 */

public abstract class ProxyActivity extends SupportActivity {
    //设置根布局
    public abstract MinDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            //加载根fragment
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //java 中垃圾回收
        System.gc();
        System.runFinalization();
    }
}
