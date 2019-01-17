package com.xoxo.bgms.common.annotation;

import java.lang.annotation.*;

/**
 * @Package com.learn.ssmmodules.common.annotation
 * @Description 验签注解（在需要验签的方法上放上此注解即可）
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-09 17:09
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface VerifySign {
}
