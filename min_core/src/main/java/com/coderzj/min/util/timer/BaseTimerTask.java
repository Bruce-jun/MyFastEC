package com.coderzj.min.util.timer;

import java.util.TimerTask;

/**
 * Created by 邹俊 on 2018/1/25.
 * 计时器
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
       if (mITimerListener!=null){
           mITimerListener.onTimer();
       }
    }
}
