package com.coderzj.min.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by 邹俊 on 2018/3/29.
 *  BannerHolder
 */

public class BannerHolderCreator  implements CBViewHolderCreator<ImageCreator>{

    @Override
    public ImageCreator createHolder() {
        return new ImageCreator();
    }
}
