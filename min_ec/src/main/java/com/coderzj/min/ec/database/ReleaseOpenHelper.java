package com.coderzj.min.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 邹俊 on 2018/3/9.
 * 数据库帮助类
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper{
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
