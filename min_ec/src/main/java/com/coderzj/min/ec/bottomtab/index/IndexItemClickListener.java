package com.coderzj.min.ec.bottomtab.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.bottomtab.detail.GoodsDetailDelegate;

/**
 * Created by 邹俊 on 2018/3/30.
 *  Recycler 每个Item的点击事件
 */

public class IndexItemClickListener extends SimpleClickListener {

    final MinDelegate DELEGATE;

    private IndexItemClickListener(MinDelegate delegate) {
        DELEGATE = delegate;
    }
    public  static SimpleClickListener create(MinDelegate delegate){
        return  new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
          DELEGATE.start(new GoodsDetailDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
