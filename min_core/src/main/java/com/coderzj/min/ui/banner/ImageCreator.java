package com.coderzj.min.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by 邹俊 on 2018/3/29.
 *
 */

public class ImageCreator implements Holder<String> {
   private AppCompatImageView mImageView=null;

    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView ;
    }

    private  final RequestOptions mOptions=new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(mOptions)
                .into(mImageView);
    }
}
