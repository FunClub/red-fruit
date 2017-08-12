package com.taomei.dao.dtos.discussion;

import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.discussion.ParentDiscussion;

/**
 * 插入父级评论的dto
 */
public class InsertParentDiscussionDto {
    private ParentDiscussion parentDiscussion;
    private NoticeArt noticeArt;

    public ParentDiscussion getParentDiscussion() {
        return parentDiscussion;
    }

    public void setParentDiscussion(ParentDiscussion parentDiscussion) {
        this.parentDiscussion = parentDiscussion;
    }

    public NoticeArt getNoticeArt() {
        return noticeArt;
    }

    public void setNoticeArt(NoticeArt noticeArt) {
        this.noticeArt = noticeArt;
    }
}
