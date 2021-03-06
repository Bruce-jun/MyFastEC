package com.diabin.min.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 邹俊 on 2018/3/24.
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String PackageName();

    Class<?> entryTemplete();

 }
