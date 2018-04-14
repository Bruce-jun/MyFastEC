package com.coderzj.min.ec.bottomtab.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderzj.min.ui.recycle.DataConverter;
import com.coderzj.min.ui.recycle.ItemTypes;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.coderzj.min.ui.recycle.MultipleItemEntityTypes;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/4/1.
 */
public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> coverter() {
        final ArrayList<MultipleItemEntity> arrayList=new ArrayList<>();
        final JSONArray objects= JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
         final int size=objects.size();
         for (int i=0;i<size;i++){
             final JSONObject data=objects.getJSONObject(i);
             final int id=data.getInteger("id");
             final String name=data.getString("name");

             final  MultipleItemEntity entity=MultipleItemEntity.builder()
                     .setFeild(MultipleItemEntityTypes.ITEM_TYPE, ItemTypes.VERTICAL_LIST)
                     .setFeild(MultipleItemEntityTypes.ID,id)
                     .setFeild(MultipleItemEntityTypes.NAME,name)
                     .setFeild(MultipleItemEntityTypes.TAG,false)
                     .build();
             arrayList.add(entity);

         }
         //设置第一个默认被选中
          arrayList.get(0).setField(MultipleItemEntityTypes.TAG,true);

        return arrayList;
    }
}
