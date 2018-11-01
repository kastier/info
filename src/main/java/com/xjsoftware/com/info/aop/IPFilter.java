package com.xjsoftware.com.info.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface IPFilter {
	String TimesLimit() default "20";
	String ExpireSeconds() default "5";
}
