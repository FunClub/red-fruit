package com.taomei.service.share.anotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 拥有此注解的方法会插入点赞动态通知
 */
@Target(ElementType.METHOD)
public @interface InsertArtThumbsUpNoticeArt {

}
