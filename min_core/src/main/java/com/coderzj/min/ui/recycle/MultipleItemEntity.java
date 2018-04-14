package com.coderzj.min.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by 邹俊 on 2018/3/28.
 * Entity与Bean是一个意思，看喜欢怎么使用
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_REFERENCE = new ReferenceQueue<>();
    //LinkedHashMap<Object,Object>保存RecycleView的数据
    private final LinkedHashMap<Object,Object> MUlTIPLE_FIELDS=new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERECCE
            =new SoftReference<>(MUlTIPLE_FIELDS,ITEM_REFERENCE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        MUlTIPLE_FIELDS.putAll(fields);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERECCE.get().get(MultipleItemEntityTypes.ITEM_TYPE);
    }

    //获取任意数据类型的数据
    @SuppressWarnings("unchecked")
    public final <T>T getField(Object key){
        return (T) FIELDS_REFERECCE.get().get(key);
    }

    //返回整个LinkedHashMap
    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERECCE.get();
    }


    public final MultipleItemEntity setField(Object key,Object value){
        FIELDS_REFERECCE.get().put(key, value);
        return this;
    }
}
