package com.coderzj.min.delegates;

/**
 * Created by 邹俊 on 2017/12/17.
 * 以后需要使用的类
 */

public abstract class MinDelegate extends PremissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends MinDelegate> T getParentDelegete(){
        return (T) getParentFragment();
    }

}
