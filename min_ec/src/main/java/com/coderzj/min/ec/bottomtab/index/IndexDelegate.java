package com.coderzj.min.ec.bottomtab.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.ui.recycle.BaseDecorator;
import com.coderzj.min.ui.refresh.RefreshHandle;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Created by 邹俊 on 2018/3/26.
 *  首页
 */

public class IndexDelegate extends ButtomItemdelegate {

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout=null;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar=null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIcon_San=null;
    @BindView(R2.id.et_index_search)
    AppCompatEditText mEditText_Search=null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIcon_Message=null;

    private RefreshHandle mRefreshHandle=null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        intRecycleView();
        mRefreshHandle= RefreshHandle.create(mRefreshLayout,mRecyclerView,
                new IndexDataConverter());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegeta_index;
    }

    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_bright
        );
        mRefreshLayout.setProgressViewOffset(true,150,300);
    }

    private void intRecycleView(){
        final GridLayoutManager manager=new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecorator.create(ContextCompat.getColor(getContext(),R.color.recyeler_divider),5));
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(this.getParentDelegete()));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        mRefreshHandle.firstpage("index_data.json");
    }
}
