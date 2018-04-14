package com.coderzj.festec.example.generators;

import com.coderzj.min.wechat.template.AppRegisterTemplate;
import com.diabin.min.annotations.AppRegisterGenerator;

/**
 * Created by 邹俊 on 2018/3/24.
 *
 */
@AppRegisterGenerator(
        PackageName = "com.coderzj.festec.example",
        AppRegisterTemplete = AppRegisterTemplate.class
)
public interface WeChatAppRegister {
}
