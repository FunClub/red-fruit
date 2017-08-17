package com.taomei.dao.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户设置文档
 */
@Document(collection = "settings")
public class Settings {
    @Id
    private String settingId;
    private String userId;
    private int albumViewType;
    private int albumSortType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAlbumViewType() {
        return albumViewType;
    }

    public void setAlbumViewType(int albumViewType) {
        this.albumViewType = albumViewType;
    }

    public int getAlbumSortType() {
        return albumSortType;
    }

    public void setAlbumSortType(int albumSortType) {
        this.albumSortType = albumSortType;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }
}
