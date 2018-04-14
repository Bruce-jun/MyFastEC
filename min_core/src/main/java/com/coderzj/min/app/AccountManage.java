package com.coderzj.min.app;

import com.coderzj.min.util.storage.MinPreference;

/**
 * Created by 邹俊 on 2018/3/20.
 *   用户信息管理类
 */

public class AccountManage {
    private enum SignTag{
        SIGN_TAG
    }
     //保存用户登录状态,登录后调用
     public static void setSignState(boolean signState){
         MinPreference.setAppFlag(SignTag.SIGN_TAG.name(),true);
     }
     //获取用户登录状态
     public static boolean isSignState(){
       return MinPreference.getAppFlag(SignTag.SIGN_TAG.name());
     }

     public static void checkAccount(IUserChecker userChecker){
         if (isSignState()){
             userChecker.onSignIn();
         } else {
             userChecker.onNotSignIn();
         }
     }

}
