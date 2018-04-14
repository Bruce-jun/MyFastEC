package com.coderzj.min.ec.bottomtab.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 邹俊 on 2018/4/1.
 */
public class VerticalContentDelegate extends MinDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";//表示左侧点击了哪个Item,右侧切换到相应ID的RecycleView
    private int mContentId = -1;
    List<SectionBean> mBeans=new ArrayList<>();


    @BindView(R2.id.rv_vertical_content)
    RecyclerView mRecyclerView=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if (arg != null) {
            mContentId = arg.getInt(ARG_CONTENT_ID);
        }
    }

    public static VerticalContentDelegate newInstance(int contentId) {
        final Bundle arg = new Bundle();
        arg.putInt(ARG_CONTENT_ID, contentId);
        final VerticalContentDelegate delegate = new VerticalContentDelegate();
        delegate.setArguments(arg);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
    //初始化数据
    private void initData(){
        RestClient.builder()
                .url("sort_content_data_1.json?contentId"+mContentId)
                .load(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("VerticalContentDelegate", "onSuccess: +"+response);
                        mBeans=new ContentDataConverter().converter(response);
                        final SectionContentAdapter adapter=
                               new SectionContentAdapter(R.layout.item_section_content,R.layout.item_section_header,mBeans);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
