package com.coderzj.min.ec.bottomtab.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.coderzj.min.ec.R;
import com.coderzj.min.ui.recycle.RgbValue;

/**
 * Created by 邹俊 on 2018/3/30.
 *
 */
@SuppressWarnings("unused")//表示知道没有被使用，但也知道它的作用
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY=0;
    //颜色变化速度
    private int mShowSpeed=3;
    //定义变化的颜色
    private  final RgbValue mRgbValue=RgbValue.create(0,153,204);


    //实例化必须要有一个构造方法（必须是两个构造参数的），否则会报错
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //协调布局要依赖的一个布局
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        //因为是要依赖我们的Recycler滑动，所以要依赖的布局就是我们的Recycler(IndexDelegate中的)
        return dependency.getId()== R.id.rv_index;
    }

    //表示接管事件
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    //处理我们具体的逻辑，
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //滑动增加的距离
        mDistanceY+=dy;
        //获取toolbar的高度
        final int targetHeight=child.getBottom();
        if (mDistanceY>0 &&mDistanceY<targetHeight){
            final float scale=(float)mDistanceY/targetHeight;
            final float alpha=scale*255;
            child.setBackgroundColor(Color.argb((int)alpha,mRgbValue.red(),mRgbValue.green(),mRgbValue.blue()));
        }else if (mDistanceY>targetHeight){
            child.setBackgroundColor(Color.rgb(mRgbValue.red(),mRgbValue.green(),mRgbValue.blue()));
        }


    }
}
