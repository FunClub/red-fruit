package com.taomei.dao.dtos.noticeart;

/**
 * 给动态点赞的dto
 */
public class ArtThumbsUpDto {
    private String artId;
    private String artUserId;
    private String artType;
    private String thumbsUpUserId;
    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getArtUserId() {
        return artUserId;
    }

    public void setArtUserId(String artUserId) {
        this.artUserId = artUserId;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }
}
