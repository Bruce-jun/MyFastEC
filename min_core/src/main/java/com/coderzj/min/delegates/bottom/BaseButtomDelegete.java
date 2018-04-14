package com.coderzj.min.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.coderzj.min.R;
import com.coderzj.min.R2;
import com.coderzj.min.delegates.MinDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by 邹俊 on 2018/3/26.
 * 底部界面的基类
 */
public abstract class BaseButtomDelegete extends MinDelegate implements View.OnClickListener {
    private final ArrayList<ButtomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<ButtomItemdelegate> TAB_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<ButtomTabBean, ButtomItemdelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentItem = 0; //当前界面
    private int mIndexItem = 0; //进入的以一个界面
    private int mTabColor = Color.RED;

    @BindView(R2.id.mBottomBar)
    LinearLayoutCompat mBottomBar;



    public abstract LinkedHashMap<ButtomTabBean, ButtomItemdelegate> setItems(ItemBuilder builder);
    /**
     * @return 进入应用显示的界面
     */
    public abstract int setIndexItem();

    /**
     * @return tab点击后的颜色
     */
    @ColorInt
    public abstract int setColor();



    @Override
    public Object setLayout() {
        return R.layout.buttom_delegate_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexItem = setIndexItem();
        if (setColor() != 0) {
            mTabColor = setColor();
        }
        final ItemBuilder builder = new ItemBuilder();
        final LinkedHashMap<ButtomTabBean, ButtomItemdelegate> maps = setItems(builder);
        ITEMS.putAll(maps);
        for (Map.Entry<ButtomTabBean, ButtomItemdelegate> item : ITEMS.entrySet()) {
            final ButtomTabBean key = item.getKey();
            final ButtomItemdelegate value = item.getValue();
            TAB_BEANS.add(key);
            TAB_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.buttom_item_icon_title_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个tab的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView icon = (IconTextView) item.getChildAt(0);
            final IconTextView tv = (IconTextView) item.getChildAt(1);
            final ButtomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            icon.setText(bean.getIcon());
            tv.setText(bean.getTitle());
            if (i == mIndexItem) {
                icon.setTextColor(mTabColor);
                tv.setTextColor(mTabColor);
            }

        }
        //初始化每个TAB对应的界面
        final ISupportFragment[] fragments = TAB_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.buttom_frame, mIndexItem, fragments);
    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final IconTextView itemTitle = (IconTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }

    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mTabColor);
        final IconTextView itemTitle = (IconTextView) item.getChildAt(1);
        itemTitle.setTextColor(mTabColor);
        //展示和隐藏Fragment 参数一为要展示的  参数二为要隐藏的
        getSupportDelegate().showHideFragment(TAB_DELEGATES.get(tag), TAB_DELEGATES.get(mCurrentItem));
        //注意先后顺序
        mCurrentItem = tag;
    }
}
