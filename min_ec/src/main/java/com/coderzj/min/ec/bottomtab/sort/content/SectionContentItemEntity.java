package com.coderzj.min.ec.bottomtab.sort.content;

/**
 * Created by 邹俊 on 2018/4/1.
 */
public class SectionContentItemEntity {
    private int mGoodsId;
    private String mGoodsName; //商品名称
    private String mGoodsThumb;//商品图片URL

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        mGoodsId = goodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        mGoodsName = goodsName;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        mGoodsThumb = goodsThumb;
    }
}
