package com.coderzj.min.delegates.webview.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by 邹俊 on 2018/4/11.
 * 事件管理
 */
public class EventManage {
    private static final HashMap<String,Event> EVENT_HASH_MAP=new HashMap<>();

    private EventManage() {
    }

    private final static class Holder{
        private final static EventManage EVENT_MANAGE=new EventManage();
    }

    public static EventManage getInstance(){
        return Holder.EVENT_MANAGE;
    }

    /**
     * 添加事件
     */
    public EventManage addEvents(@NonNull String key,@NonNull Event event){
        EVENT_HASH_MAP.put(key,event);
        return this;
    }

    /**
     * 创建事件
     */
    public Event createEvent(@NonNull String action){
       final Event event=EVENT_HASH_MAP.get(action);
       if (event== null){
         return new UndefineEvent();
       }
       return event;
    }
}
