package com.taomei.web.share.anotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
/**
 * 被注解的方法会为目标的第一个参数设置用户情侣Id
 */
public @interface SetHalfId {
}
