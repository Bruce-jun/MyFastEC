package com.coderzj.min.ec.bottomtab.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.ec.bottomtab.sort.SortDelegate;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 邹俊 on 2018/4/1.
 * 分类左侧垂直的列表
 */
public class VerticalListDelegate extends MinDelegate {


    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_menu_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecycleView();
    }

    private  void initRecycleView(){
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list_data.json")
                .load(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final ArrayList<MultipleItemEntity> arrayList =
                                new VerticalListDataConverter().setJsonData(response).coverter();
                        final SortDelegate sortDelegate= (SortDelegate) getParentFragment();
                        VerticalListRecyclerAdapter adapter=new VerticalListRecyclerAdapter(arrayList,sortDelegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();

    }
}
