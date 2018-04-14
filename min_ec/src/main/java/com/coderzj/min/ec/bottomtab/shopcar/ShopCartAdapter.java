package com.coderzj.min.ec.bottomtab.shopcar;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.coderzj.min.ec.R;
import com.coderzj.min.net.RestClient;
import com.coderzj.min.net.callback.ISuccess;
import com.coderzj.min.ui.recycle.MultipleHolder;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.coderzj.min.ui.recycle.MultipleRecyclerAdapter;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by 邹俊 on 2018/4/12.
 * <p>
 * "count": 1,
 * "desc": "阳极氧化铝合金外壳+ABS材质",
 * "id": 3,
 * "price": 99,
 * "thumb": "https://i8.mifile.cn/v1/a1/2ca61893-68b9-d0a0-5f85-bdc28e655a3e.jpg?width=120&height=120",
 * "title": "无线鼠标"
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {
    private boolean mIsSelectAll = false;

    private ICartItemListener mICartItemListener = null;

    private double mTotalPrice = 0.0;


    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public void setIsSelectAll(boolean isSelectAll) {
        mIsSelectAll = isSelectAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        mICartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        for (MultipleItemEntity entity : data) {
            boolean isSelect = entity.getField(ShopCartItemBean.IS_SELECT);
            if (isSelect) {
                double price = entity.getField(ShopCartItemBean.PRICE);
                int count = entity.getField(ShopCartItemBean.COUNT);
                double itemTotalPrice = price * count;
                mTotalPrice += itemTotalPrice;
                Log.d("mTotalPrice", "" + mTotalPrice);
            }
        }
        addItemType(ShopCartItemType.SHOP_CART, R.layout.item_shop_car);

    }

    @Override
    protected void convert(MultipleHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        if (mICartItemListener != null) {
            mICartItemListener.onItemClick(mTotalPrice);
        }
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART:
                final int count = entity.getField(ShopCartItemBean.COUNT);
                final String desc = entity.getField(ShopCartItemBean.DESC);
                final double price = entity.getField(ShopCartItemBean.PRICE);
                final String thumb = entity.getField(ShopCartItemBean.THUMB);
                final String title = entity.getField(ShopCartItemBean.TITLE);


                //取出控件
                final AppCompatImageView imgThumb = holder.getView(R.id.iv_item_shop_car);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_title_item_shop_car);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_descr_item_shop_car);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_price_item_shop_car);
                final IconTextView iconMinus = holder.getView(R.id.icon_minus_item_shop_car);
                final IconTextView iconPlus = holder.getView(R.id.icon_plus_item_shop_car);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_numb_item_shop_car);
                final IconTextView iconIsSelected = holder.getView(R.id.itv_item_shop_car);

                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));


                //再左侧渲染之前进行
                entity.setField(ShopCartItemBean.IS_SELECT, mIsSelectAll);

                final boolean is_select = entity.getField(ShopCartItemBean.IS_SELECT);
                if (is_select) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(mContext, R.color.blue_like));
                } else {
                    iconIsSelected.setTextColor(ContextCompat.getColor(mContext, R.color.wet_chat));
                }

                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final boolean isSelect = entity.getField(ShopCartItemBean.IS_SELECT);
                        int itemcount = Integer.parseInt(tvCount.getText().toString());
                        if (!mIsSelectAll) {

                        }else {

                        }
                        if (!isSelect) {
                            entity.setField(ShopCartItemBean.IS_SELECT, true);
                            iconIsSelected.setTextColor(ContextCompat.getColor(mContext, R.color.blue_like));

                            double itemTotalPrice = price * itemcount;
                            mTotalPrice += itemTotalPrice;
                            Log.d("mTotalPrice", "" + mTotalPrice);
                            mICartItemListener.onItemClick(itemTotalPrice);

                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(mContext, R.color.wet_chat));
                            entity.setField(ShopCartItemBean.IS_SELECT, false);

                            double itemTotalPrice = price * itemcount;
                            mTotalPrice -= itemTotalPrice;
                            Log.d("mTotalPrice", "" + mTotalPrice);
                            mICartItemListener.onItemClick(itemTotalPrice);
                        }
                    }
                });

                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(ShopCartItemBean.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("info.json")
                                    .load(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            boolean is_select = entity.getField(ShopCartItemBean.IS_SELECT);
                                            if (is_select && mICartItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemPrice = countNum * price;
                                                mICartItemListener.onItemClick(itemPrice);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(ShopCartItemBean.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) >= 1) {
                            RestClient.builder()
                                    .url("info.json")
                                    .load(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum++;
                                            tvCount.setText(String.valueOf(countNum));
                                            boolean is_select = entity.getField(ShopCartItemBean.IS_SELECT);
                                            if (is_select && mICartItemListener != null) {
                                                mTotalPrice = mTotalPrice + price;
                                                final double itemPrice = countNum * price;
                                                mICartItemListener.onItemClick(itemPrice);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                break;
            default:
                break;
        }
    }


}
