package com.coderzj.min.ec.bottomtab.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderzj.min.ec.R;

import java.util.List;

/**
 * Created by 邹俊 on 2018/4/1.
 */
public class SectionContentAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {

    public SectionContentAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);

    }
    private  final RequestOptions mOptions=new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
       helper.setText(R.id.header,item.header);
       helper.setVisible(R.id.more,item.isMore());
       helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
      SectionContentItemEntity entity=item.t;
       final String name=entity.getGoodsName();
       final String url=entity.getGoodsThumb();
       helper.setText(R.id.tv_item_section,name);
       final AppCompatImageView imageView=helper.getView(R.id.iv_item_section);
       Glide.with(mContext)
                .load(url)
               .apply(mOptions)
                .into(imageView);

    }
}
