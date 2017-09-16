package com.taomei.dao.dtos.circle;

import com.taomei.dao.dtos.share.ArtInfoDto;
import com.taomei.dao.entities.circle.Post;


/**
 * 显示一个帖子的dto
 */
public class ShowPostDto{
    private Post post;
    private ArtInfoDto artInfo;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArtInfoDto getArtInfo() {
        return artInfo;
    }

    public void setArtInfo(ArtInfoDto artInfo) {
        this.artInfo = artInfo;
    }
}
