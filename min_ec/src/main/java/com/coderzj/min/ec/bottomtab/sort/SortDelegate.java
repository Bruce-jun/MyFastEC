package com.coderzj.min.ec.bottomtab.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.bottomtab.sort.content.VerticalContentDelegate;
import com.coderzj.min.ec.bottomtab.sort.list.VerticalListDelegate;

/**
 * Created by 邹俊 on 2018/3/27.
 *  分类界面
 */

public class SortDelegate extends ButtomItemdelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegeta_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //懒加载布局
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        VerticalListDelegate listDelegate= new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.delegate_sort_list,listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.delegate_sort_content, VerticalContentDelegate.newInstance(1));
    }
}
