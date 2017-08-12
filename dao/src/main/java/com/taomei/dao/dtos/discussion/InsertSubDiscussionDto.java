package com.taomei.dao.dtos.discussion;

import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.discussion.SubDiscussion;

/**
 * 插入子评论的dto
 */
public class InsertSubDiscussionDto {
    private SubDiscussion subDiscussion;
    private String discussionId;
    private NoticeArt noticeArt;
    /**
     * 给自评论回复的用户id(回复谁，为空则是回复父评论)
     */

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public SubDiscussion getSubDiscussion() {
        return subDiscussion;
    }

    public void setSubDiscussion(SubDiscussion subDiscussion) {
        this.subDiscussion = subDiscussion;
    }

    public NoticeArt getNoticeArt() {
        return noticeArt;
    }

    public void setNoticeArt(NoticeArt noticeArt) {
        this.noticeArt = noticeArt;
    }
}
