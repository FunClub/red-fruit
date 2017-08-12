package com.taomei.service.share.aspect;

import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.repository.NoticeArtRepository;
import com.taomei.service.share.utils.TimeUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 为指定的切点添加插入点赞动态
 */
@Aspect
@Component
public class NoticeArtAspect {
    @Autowired
    private NoticeArtRepository noticeArtRepository;

    @After("@annotation(com.taomei.service.share.anotaions.InsertArtThumbsUpNoticeArt)" +
            "&&args(noticeArt)")
    public void InsertArtThumbsUpNoticeArt(NoticeArt noticeArt) throws Exception {
        noticeArt.setDate(TimeUtil.getSimpleTime());
        NoticeArt newNoticeArt=noticeArtRepository.insert(noticeArt);
        if(newNoticeArt==null)throw  new Exception("插入点赞通知失败");
    }
}
