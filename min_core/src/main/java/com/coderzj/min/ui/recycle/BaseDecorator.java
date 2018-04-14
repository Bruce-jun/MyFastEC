package com.coderzj.min.ui.recycle;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by 邹俊 on 2018/3/30.
 *
 */

public class BaseDecorator extends DividerItemDecoration {

    private BaseDecorator(@ColorInt int color, int size) {
      setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecorator create(@ColorInt int color, int size){
      return  new BaseDecorator(color,size);
    }
}
