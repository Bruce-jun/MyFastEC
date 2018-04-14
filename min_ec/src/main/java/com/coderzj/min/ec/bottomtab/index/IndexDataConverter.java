package com.coderzj.min.ec.bottomtab.index;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderzj.min.ui.recycle.DataConverter;
import com.coderzj.min.ui.recycle.ItemTypes;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.coderzj.min.ui.recycle.MultipleItemEntityTypes;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/3/28.
 *   转换主页的数据
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> coverter() {
        final JSONArray dataArray= JSON.parseObject(getJsonData()).getJSONArray("data");
        int size=dataArray.size();
        for (int i=0;i<size;i++){
           final JSONObject object=dataArray.getJSONObject(i);
            final String imgUrl=object.getString("imageUrl");
            final String text=object.getString("text");
            final int spanSize=object.getInteger("spanSize");
            final int id=object.getInteger("goodsId");
            final JSONArray banners=object.getJSONArray("banners");
            final ArrayList<String> bannerImages=new ArrayList<>();
            int type=0;//数据类型
            if (imgUrl==null&&text!=null){
                type= ItemTypes.TEXT;
            }else if (imgUrl!=null&&text==null){
                type=ItemTypes.IMAGE;
            }else if (imgUrl!=null){
                type=ItemTypes.TEXT_IMAGE;
            }else if (banners!=null){
                type=ItemTypes.BANNER;
                final int  bannersize=banners.size();
                for (int j=0;j<bannersize;j++){
                    String bannerImageUrl=banners.getString(j);
                    Log.d("bannerImageUrl",bannerImageUrl);
                    bannerImages.add(bannerImageUrl);
                }
            }
            final  MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setItemType(type)
                    .setFeild(MultipleItemEntityTypes.SPAN_SIZE,spanSize)
                    .setFeild(MultipleItemEntityTypes.ID,id)
                    .setFeild(MultipleItemEntityTypes.IMAGE_URL,imgUrl)
                    .setFeild(MultipleItemEntityTypes.TEXT,text)
                    .setFeild(MultipleItemEntityTypes.BANNERS,bannerImages)
                    .build();
            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
