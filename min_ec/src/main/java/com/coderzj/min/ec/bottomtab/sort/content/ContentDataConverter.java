package com.coderzj.min.ec.bottomtab.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邹俊 on 2018/4/1.
 * 内容数据解析器
 */
public class ContentDataConverter  {

    final List<SectionBean> converter(String json){
        final ArrayList<SectionBean> list=new ArrayList<>();
        final JSONArray jsonArray= JSON.parseObject(json).getJSONArray("data");
        final int size=jsonArray.size();
        for (int i=0;i<size;i++){
            JSONObject goodObject=jsonArray.getJSONObject(i);
            final int id=goodObject.getInteger("id");
            final String title=goodObject.getString("section");

            final SectionBean sectionBean=new SectionBean(true,title);
            sectionBean.setId(id);
            sectionBean.setMore(true);

            list.add(sectionBean);

            final JSONArray goods=goodObject.getJSONArray("goods");
            int goodSize=goods.size();
            for (int j=0;j<goodSize;j++){
                JSONObject object=goods.getJSONObject(j);
                final int  goodsId=object.getInteger("goods_id");
                final String goodsName=object.getString("goods_name");
                final String goodsThumb=object.getString("goods_thumb");
                SectionContentItemEntity entity=new SectionContentItemEntity();
                entity.setGoodsId(goodsId);
                entity.setGoodsName(goodsName);
                entity.setGoodsThumb(goodsThumb);
                list.add(new SectionBean(entity));
            }
        }
        return list;
    }
}
