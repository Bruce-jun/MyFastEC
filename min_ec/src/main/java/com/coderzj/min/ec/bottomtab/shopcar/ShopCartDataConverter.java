package com.coderzj.min.ec.bottomtab.shopcar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderzj.min.ui.recycle.DataConverter;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.coderzj.min.ui.recycle.MultipleItemEntityTypes;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/4/12.
 * 数据解析
 *
 "count": 1,
 "desc": "阳极氧化铝合金外壳+ABS材质",
 "id": 3,
 "price": 99,
 "thumb": "https://i8.mifile.cn/v1/a1/2ca61893-68b9-d0a0-5f85-bdc28e655a3e.jpg?width=120&height=120",
 "title": "无线鼠标"
 */
public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> coverter() {
        final ArrayList<MultipleItemEntity> Entities=new ArrayList<>();
        final JSONArray objects= JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=objects.size();
        for (int i=0;i<size;i++){
            JSONObject o =objects.getJSONObject(i);
            int count=o.getInteger("count");
            int id=o.getInteger("id");
            double price=o.getDouble("price");
            String title=o.getString("title");
            String desc=o.getString("desc");
            String thumb=o.getString("thumb");

            final MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setFeild(MultipleItemEntityTypes.ITEM_TYPE,ShopCartItemType.SHOP_CART)
                    .setFeild(ShopCartItemBean.ID,id)
                    .setFeild(ShopCartItemBean.COUNT,count)
                    .setFeild(ShopCartItemBean.PRICE,price)
                    .setFeild(ShopCartItemBean.TITLE,title)
                    .setFeild(ShopCartItemBean.DESC,desc)
                    .setFeild(ShopCartItemBean.THUMB,thumb)
                    .setFeild(ShopCartItemBean.IS_SELECT,false)
                    .setFeild(ShopCartItemBean.POSITION,i)
                    .build();

            Entities.add(entity);
        }

        return Entities;
    }
}
