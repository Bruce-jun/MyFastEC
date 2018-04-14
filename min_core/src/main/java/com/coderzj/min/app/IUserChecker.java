package com.coderzj.min.app;

/**
 * Created by 邹俊 on 2018/3/20.
 *   检查是否有用户信息的接口，就是用户是否登录
 */

public interface IUserChecker {

    void  onSignIn();//有用户信息,已经登录的回调

    void  onNotSignIn();//没有用户信息

}
