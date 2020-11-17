package com.lsf.hw_0902.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface MyCache {
    String prefixKey() default "result_cache";

    long expireTime() default 60L;
}
