package com.coderzj.min.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.coderzj.min.R;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/3/29.
 *  banner的创建
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener listener){
        convenientBanner
                .setPages(new BannerHolderCreator(),banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(listener)//点击事件
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000) //延迟时间
                .setCanLoop(true);
    }
}
