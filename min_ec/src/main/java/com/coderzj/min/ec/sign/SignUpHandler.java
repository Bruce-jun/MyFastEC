package com.coderzj.min.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coderzj.min.app.AccountManage;
import com.coderzj.min.ec.database.DatabaseManager;
import com.coderzj.min.ec.database.UserProfile;

/**
 * Created by 邹俊 on 2018/3/20.
 *   登录的帮助类
 *   {
       "code": 0,
       "message": "OK",
            "data": {
            "userId": 1,
            "name": "猿猿",
            "avatar": "http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0",
            "gender": "男",
            "address": "西安"
            }
 }
 */

public class SignUpHandler {
    public static void onSignIn(String response, ISignListener iSignListener){
        //保存登录状态
        AccountManage.setSignState(true);
        //登录成功的回调
        iSignListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener iSignListener){
       final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
       final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");

        final UserProfile userProfile=new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //注册成功的回调
        iSignListener.onSignUpSuccess();
    }
}
