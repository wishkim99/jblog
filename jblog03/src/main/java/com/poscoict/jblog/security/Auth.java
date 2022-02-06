package com.poscoict.jblog.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.servlet.ServletContext;

import com.poscoict.jblog.vo.BlogVo;


@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Auth {
//	public String values() default "USER";
	
	public boolean id() default false;
//	public boolean test() default false;
}
