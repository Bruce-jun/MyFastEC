package com.coderzj.min.ec.bottomtab.shopcar;

/**
 * Created by 邹俊 on 2018/4/14.
 */
public interface ICartItemListener {

    void  onItemClick(double itemTotalprice);//itemTotalprice  单个项目的总价  也就是 单价*数量
    //当前选中的Item需要计算价钱
}
