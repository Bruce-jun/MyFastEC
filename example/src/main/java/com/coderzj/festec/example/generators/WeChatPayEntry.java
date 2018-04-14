package com.coderzj.festec.example.generators;

import com.coderzj.min.wechat.template.WXPayEntryTemplate;
import com.diabin.min.annotations.PayEntryGenerator;

/**
 * Created by 邹俊 on 2018/3/24.
 *
 */
@PayEntryGenerator(
        PackageName = "com.coderzj.festec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
