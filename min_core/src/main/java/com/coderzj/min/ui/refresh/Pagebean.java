package com.coderzj.min.ui.refresh;

/**
 * Created by 邹俊 on 2018/3/29.
 * 页面的信息数据
 */

public final class Pagebean {
    //当前是第几页
    private int mPageIndex;
    //总数居条数
    private int mTotal;
    //一页显示几条数据
    private int mPageSize;
    //当前显示了几条数据
    private int mCurrentCount;
    //加载的延迟
    private int mdelayed;

    public int getPageIndex() {
        return mPageIndex;
    }

    public Pagebean setPageIndex(int pageIndex) {
        mPageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public Pagebean setTotal(int total) {
        mTotal = total;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public Pagebean setPageSize(int pageSize) {
        mPageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public Pagebean setCurrentCount(int currentCount) {
        mCurrentCount = currentCount;
        return this;
    }

    public int getDelayed() {
        return mdelayed;
    }

    public Pagebean setDelayed(int mdelayed) {
        this.mdelayed = mdelayed;
        return this;
    }

    //第一次加载时调用，也就是当前页面加1
    Pagebean index() {
        mPageIndex++;
        return this;

    }
}
