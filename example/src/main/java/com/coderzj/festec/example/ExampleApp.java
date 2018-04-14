package com.coderzj.festec.example;

import android.app.Application;

import com.coderzj.festec.example.event.TestEvent;
import com.coderzj.min.app.Min;
import com.coderzj.min.ec.database.DatabaseManager;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by 邹俊 on 2017/12/12.
 *
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //全局的项目配置
        Min.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://192.168.1.106:8080/RestServer/data/")
                .withJavaScriptInterface("Min")
                .withWebEvent("test",new TestEvent())
//                .withInterceptor()
                .configure();
        initStetho();
        DatabaseManager.getInstance().initDao(this);//数据库初始化
    }

    private void initStetho(){
        Stetho.initialize(
             Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                     .build()
        );
    }
}
