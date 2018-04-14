package com.coderzj.min.ui.recycle;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderzj.min.R;
import com.coderzj.min.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邹俊 on 2018/3/29.
 *  Recycle适配器
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {
    //保障Banner只加载一次
    private boolean isFirstBanner=false;

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private  final RequestOptions mOptions=new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    private void init() {
        addItemType(ItemTypes.TEXT, R.layout.item_multiple_text);
        addItemType(ItemTypes.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemTypes.TEXT_IMAGE, R.layout.item_multiple_text_image);
        addItemType(ItemTypes.BANNER, R.layout.item_multiple_banner);
        //设置宽度的监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleHolder createBaseViewHolder(View view) {
        return MultipleHolder.creat(view);
    }

    public  static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public  static MultipleRecyclerAdapter create(DataConverter converter){
        Log.d("MultipleRecyclerAdapter","Create!!");
        return new MultipleRecyclerAdapter(converter.coverter());
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleItemEntityTypes.SPAN_SIZE);
    }

    @Override
    protected void convert(MultipleHolder holder, MultipleItemEntity entity) {
       final String text;
       final String imageUrl;
       final ArrayList<String> banners;
       switch (holder.getItemViewType()){
           case ItemTypes.TEXT:
               text=entity.getField(MultipleItemEntityTypes.TEXT);
               holder.setText(R.id.text_item_multiple,text);
               break;
           case ItemTypes.IMAGE:
               imageUrl=entity.getField(MultipleItemEntityTypes.IMAGE_URL);
               Log.d("MultipleRecyclerAdapter", "convert: "+imageUrl);
               Glide.with(mContext)
                       .load(imageUrl)
                       .apply(mOptions)
                       .into((AppCompatImageView) holder.getView(R.id.image_item_multiple));
               break;
           case ItemTypes.TEXT_IMAGE:
               text=entity.getField(MultipleItemEntityTypes.TEXT);
               imageUrl=entity.getField(MultipleItemEntityTypes.IMAGE_URL);
               Log.d("MultipleRecyclerAdapter", "convert: "+imageUrl);
               holder.setText(R.id.text_item_textimage,text);
               ImageView imageView= holder.getView(R.id.image_item_textimage);
               Glide.with(mContext)
                       .load(imageUrl)
                       .apply(mOptions)
                       .into(imageView);
               break;
           case ItemTypes.BANNER:
               if(!isFirstBanner){
                   banners=entity.getField(MultipleItemEntityTypes.BANNERS);
                   final ConvenientBanner<String> banner=holder.getView(R.id.banner_item_multiple);
                   BannerCreator.setDefault(banner,banners,this);
                   isFirstBanner=true;
               }
               break;
           default:
               break;
       }

    }

    @Override
    public void onItemClick(int position) {

    }
}
