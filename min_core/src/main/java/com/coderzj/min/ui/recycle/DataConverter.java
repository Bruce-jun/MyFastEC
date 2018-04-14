package com.coderzj.min.ui.recycle;

import java.util.ArrayList;

/**
 * Created by 邹俊 on 2018/3/28.
 *  Recycle 的数据转换的基类,对获取到的数据进行转换
 */

public abstract class DataConverter {

    protected  final ArrayList<MultipleItemEntity> ENTITYS=new ArrayList<>();

    private  String mJsonData=null;

    public abstract ArrayList<MultipleItemEntity> coverter();

    public DataConverter setJsonData(String data){
      this.mJsonData=data;
      return this;
    }

    public String getJsonData(){
        if (mJsonData==null||mJsonData.isEmpty()){
            throw new NullPointerException("Data is Null");
        }
        return mJsonData;
    }

}
