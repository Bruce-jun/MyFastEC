package com.coderzj.min.ui.recycle;

import com.google.auto.value.AutoValue;

/**
 * Created by 邹俊 on 2018/3/30.
 *  需要引入google自动化的库
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);

    }
}
