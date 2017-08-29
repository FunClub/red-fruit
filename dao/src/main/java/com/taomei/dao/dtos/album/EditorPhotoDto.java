package com.taomei.dao.dtos.album;

/**
 * 编辑器相片
 */
public class EditorPhotoDto {
    private String url;
    private String thumb;
    private String tag;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
