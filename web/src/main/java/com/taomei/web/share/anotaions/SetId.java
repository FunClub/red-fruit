package com.taomei.web.share.anotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
/**
 * 被注解的方法会为目标第一个参数设置userId，第二个参数设置用户情侣Id
 */
public @interface SetId {
}
