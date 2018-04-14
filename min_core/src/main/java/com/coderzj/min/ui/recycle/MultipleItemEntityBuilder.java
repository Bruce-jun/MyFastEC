package com.coderzj.min.ui.recycle;

import java.util.LinkedHashMap;

/**
 * Created by 邹俊 on 2018/3/28.
 *  MultipleItemEntity的建造者,也可以使用静态内部类
 */

public class MultipleItemEntityBuilder {
  private final LinkedHashMap<Object,Object> FIELDS=new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        FIELDS.clear();
    }

    public  final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleItemEntityTypes.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setFeild(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleItemEntityBuilder setFeilds(LinkedHashMap<Object,Object> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
        return  new MultipleItemEntity(FIELDS);

    }
}
