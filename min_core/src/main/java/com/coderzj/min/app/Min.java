package com.coderzj.min.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;


/**
 * Created by 邹俊 on 2017/12/12.
 *  配置全局信息
 */

public final class Min {

    public static Configurator init(Context context){
        //配置全局的Context
//       getConfiguration().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
//        return Configurator.getInstance();
        Configurator.getInstance().getMinConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations(){
        return getConfigurator().getMinConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getCofiguration(key);
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name() );
    }
    public static Handler getHandler() {
        return (Handler) getConfigurations().get(ConfigType.HANDLER);
    }

}
