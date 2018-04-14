package com.coderzj.min.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderzj.min.app.Min;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.ui.recycle.DataConverter;
import com.coderzj.min.ui.recycle.MultipleRecyclerAdapter;



/**
 * Created by 邹俊 on 2018/3/28.
 *  刷新助手
 */

public class RefreshHandle
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout mRefreshLayout;
    private final Pagebean mPagebean;
    private final RecyclerView mRecyclerView ;
    private MultipleRecyclerAdapter mAdapter=null;
    private final DataConverter mConverter;
    private final String TAG="RefreshHandle";


    private RefreshHandle(SwipeRefreshLayout swipeRefreshLayout,
                         RecyclerView recyclerView,
                         DataConverter converter,
                          Pagebean pagebean) {
        mRefreshLayout = swipeRefreshLayout;
        mPagebean = pagebean;
        mRecyclerView = recyclerView;
        mConverter = converter;
        mRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandle create(SwipeRefreshLayout swipeRefreshLayout,
                                RecyclerView recyclerView,
                                DataConverter converter
                                ){
        return new RefreshHandle(swipeRefreshLayout,recyclerView,converter,new Pagebean());
    }

    @Override
    public void onRefresh() {
          refresh();
    }


    private void refresh(){
        mRefreshLayout.setRefreshing(true);
        Min.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //处理一些网络请求
                mRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    public void firstpage(String url){
       mPagebean.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                      Log.d(TAG,response);
                      final JSONObject object= JSONObject.parseObject(response);
                      mPagebean.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));
                      //设置Adapter
                      mAdapter=MultipleRecyclerAdapter.create(mConverter.setJsonData(response));
                      mAdapter.setOnLoadMoreListener(RefreshHandle.this,mRecyclerView);
                      mRecyclerView.setAdapter(mAdapter);
                      if (mAdapter!=null) {
                          Log.d(TAG, "mAdapter is not null ");
                      }
                      mPagebean.index();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
