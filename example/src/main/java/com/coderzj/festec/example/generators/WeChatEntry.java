package com.coderzj.festec.example.generators;

import com.coderzj.min.wechat.template.WXEntryTemplate;
import com.diabin.min.annotations.EntryGenerator;

/**
 * Created by 邹俊 on 2018/3/24.
 */
@EntryGenerator(
        PackageName = "com.coderzj.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
