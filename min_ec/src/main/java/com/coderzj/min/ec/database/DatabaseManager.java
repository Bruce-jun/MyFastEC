package com.coderzj.min.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 邹俊 on 2018/3/9.
 * 数据库管理类
 */

public class DatabaseManager {
    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;

    private DatabaseManager init(Context context){
        initDao(context);
        return this;
    }
    //单例模式
    private DatabaseManager(){
    }
    public static final class Hodler{
        private static final DatabaseManager Instance=new DatabaseManager();
    }

    public static  DatabaseManager getInstance(){
        return Hodler.Instance;
    }
    //初始化数据库
    public void initDao(Context context){
        final ReleaseOpenHelper openHelper=
                new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db=openHelper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }
}
