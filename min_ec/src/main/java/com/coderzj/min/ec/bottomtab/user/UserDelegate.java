package com.coderzj.min.ec.bottomtab.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.coderzj.min.delegates.bottom.ButtomItemdelegate;
import com.coderzj.min.ec.R;

/**
 * Created by 邹俊 on 2018/3/27.
 *  分类界面
 */

public class UserDelegate extends ButtomItemdelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegeta_user;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
