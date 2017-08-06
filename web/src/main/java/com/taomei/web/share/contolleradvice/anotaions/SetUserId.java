package com.taomei.web.share.contolleradvice.anotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
/**
 * 被注解的方法会为目标第一个参数设置userId
 */
public @interface SetUserId {
}
