package com.coderzj.min.ec.bottomtab;

import android.graphics.Color;

import com.coderzj.min.delegates.bottom.BaseButtomDelegete;
import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.delegates.bottom.ButtomTabBean;
import com.coderzj.min.delegates.bottom.ItemBuilder;
import com.coderzj.min.ec.bottomtab.compass.CompassDelegate;
import com.coderzj.min.ec.bottomtab.index.IndexDelegate;
import com.coderzj.min.ec.bottomtab.shopcar.ShopCarDelegate;
import com.coderzj.min.ec.bottomtab.sort.SortDelegate;
import com.coderzj.min.ec.bottomtab.user.UserDelegate;

import java.util.LinkedHashMap;

/**
 * Created by 邹俊 on 2018/3/26.
 * 主界面
 */

public class Bottomdalegate extends BaseButtomDelegete {

    @Override
    public LinkedHashMap<ButtomTabBean, ButtomItemdelegate> setItems(ItemBuilder builder) {
        final  LinkedHashMap<ButtomTabBean, ButtomItemdelegate> ITEMS=new LinkedHashMap<>();
        ITEMS.put(new ButtomTabBean("{fa-home}","主页"),new IndexDelegate());
        ITEMS.put(new ButtomTabBean("{fa-sort}","分类"),new SortDelegate());
        ITEMS.put(new ButtomTabBean("{fa-compass}","发现"),new CompassDelegate());
        ITEMS.put(new ButtomTabBean("{fa-shopping-cart}","购物车"),new ShopCarDelegate());
        ITEMS.put(new ButtomTabBean("{fa-user}","我的"),new UserDelegate());
        return builder.addItems(ITEMS).build();
    }

    @Override
    public int setIndexItem() {
        return 0;
    }

    @Override
    public int setColor() {
        return Color.parseColor("#0099cc");
    }
}
