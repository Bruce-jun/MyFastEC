package com.coderzj.min.ec.bottomtab.shopcar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.R2;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 邹俊 on 2018/3/27.
 * 分类界面
 */

public class ShopCarDelegate extends ButtomItemdelegate implements ISuccess, ICartItemListener {

    private ShopCartAdapter mAdapter = null;
    //当前选中需要删除的数量
    private int mCurrentCount = 0;
    private int mTotalCount = 0;




    @BindView(R2.id.rv_shopcar_delegate)
    RecyclerView mRecyclerView;
    @BindView(R2.id.itv_all_select)
    IconTextView mITV_SelectAll;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubView;
    @BindView(R2.id.tv_total)
    AppCompatTextView mTv_totalPrice;

    @OnClick(R2.id.itv_all_select)
    void onSelectAll() {
        final int tag = (int) mITV_SelectAll.getTag();
        if (tag == 0) { //没有全选
            mITV_SelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_like));
            mITV_SelectAll.setTag(1);
            mAdapter.setIsSelectAll(true);
            //更新RecyelerView的数据
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());

        } else {

            mITV_SelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.wet_chat));
            mITV_SelectAll.setTag(0);
            mAdapter.setIsSelectAll(false);
            //更新RecyelerView的数据
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());


        }
    }

    @OnClick(R2.id.tv_delete)
    void deleteSelect() {//删除选中的项目
        int count = mAdapter.getItemCount();
        if (count > 0) {
            final List<MultipleItemEntity> data = mAdapter.getData();
            final List<MultipleItemEntity> needDeleteData = new ArrayList<>();
            for (MultipleItemEntity entity : data) {
                boolean isSelect = entity.getField(ShopCartItemBean.IS_SELECT);
                if (isSelect) {
                    needDeleteData.add(entity);
                }
            }
            for (MultipleItemEntity entity : needDeleteData) {
                int removePosition;//要移除的项
                final int entityPosition = entity.getField(ShopCartItemBean.POSITION);
                Log.d("entityPosition", "position~~~~" + entityPosition);
                if (entityPosition > mCurrentCount - 1) {
                    removePosition = entityPosition - (mTotalCount - mCurrentCount);
                } else {
                    removePosition = entityPosition;
                }
                if (removePosition <= mAdapter.getItemCount()) {
                    Log.d("removePosition", "position~~~~" + removePosition);
                    mAdapter.remove(removePosition);
                    mCurrentCount = mAdapter.getItemCount();
                    Log.d("mCurrentCount", "position~~~~" + mCurrentCount);
                    mAdapter.notifyDataSetChanged();
                }
            }
            checkCount();
        } else {
            Toast.makeText(getContext(), "没有数据啦！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_clear)
    void delete() {//删除所有项目
        int count = mAdapter.getItemCount();
        if (count > 0) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkCount();
        } else {
            Toast.makeText(getContext(), "没有数据啦！", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("RestrictedApi")
    private void checkCount() {
        int count = mAdapter.getItemCount();
        if (count <= 0) {
            final View view = mStubView.inflate();
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegeta_shop_car;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mITV_SelectAll.setTag(0);
        ArrayList<String> strings = new ArrayList<>();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart_data.json")
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
//        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
        final ArrayList<MultipleItemEntity> arrayList =
                new ShopCartDataConverter().setJsonData(response).coverter();
        mAdapter = new ShopCartAdapter(arrayList);
        mAdapter.setCartItemListener(this);
        mRecyclerView.setAdapter(mAdapter);
        checkCount();
    }

    @Override
    public void onItemClick(double itemTotalprice) {
        final double price = mAdapter.getTotalPrice();
        mTv_totalPrice.setText(String.valueOf(price));
        Log.d("mTotalPrice", "" + price);
    }
}
