package com.coderzj.min.app;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.coderzj.min.delegates.webview.event.Event;
import com.coderzj.min.delegates.webview.event.EventManage;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by 邹俊 on 2017/12/12.
 *   全局信息配置
 */

public class Configurator {
    private static final Handler HANDLER = new Handler();
    private static final HashMap<Object, Object> MIN_CONFIG = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();//字体图标库
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();//插值器库

    public Configurator() {
        MIN_CONFIG.put(ConfigType.CONFIG_READY.name(), false);//表示开始配置，但还没有成功
        MIN_CONFIG.put(ConfigType.HANDLER,HANDLER);
    }

    //线程安全的单例模式（静态内部类）
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<Object, Object> getMinConfigs() {
        return MIN_CONFIG;
    }

    //开始配置
    public final void configure() {
        initIcons();
        MIN_CONFIG.put(ConfigType.CONFIG_READY.name(), true);
    }

    //配置基本的网络接口
    public final Configurator withApiHost(String host) {
        MIN_CONFIG.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //初始化字体图标库
    private void initIcons(){
        if (ICONS.size()>0){
           //已经有字体了
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    //配置字体图标库
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //检查配置项，是否配置好
    private void checkConfigration() {
        final boolean isReady = (boolean) MIN_CONFIG.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Config is not ready,call config!");
        }
    }
    //配置插值器
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        MIN_CONFIG.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        MIN_CONFIG.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    /**
     * @param name 添加我们注入的名字的，
     * @return 当前对象
     */
    public final Configurator withJavaScriptInterface(@NonNull String name){
        MIN_CONFIG.put(ConfigType.JAVASCRIPT_NAME.name(),name);
        return this;
    }

    public final Configurator withWebEvent(@NonNull String name, @NonNull Event event){
        EventManage  manage=EventManage.getInstance();
        manage.addEvents(name,event);
        return this;
    }

    @SuppressWarnings("unchecked")  //该注解表示类型没有检测过，不发出警告
    final <T> T getCofiguration(Object key) {
        checkConfigration();
        return (T) MIN_CONFIG.get(key);
    }
}
