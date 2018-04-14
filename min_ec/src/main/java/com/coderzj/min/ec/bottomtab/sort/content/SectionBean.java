package com.coderzj.min.ec.bottomtab.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 邹俊 on 2018/4/1.
 *  商品内容对应的Bean类
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore;//是否更多
    private int mId; //section对应的Id

    public boolean isMore() {
        return mIsMore;
    }

    public void setMore(boolean more) {
        mIsMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }


    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }


}
