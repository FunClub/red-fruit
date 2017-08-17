package com.taomei.dao.dtos.album;

import com.taomei.dao.entities.Settings;

/**
 * 查询相册dto
 */
public class SelectHalfAlbumDto {
    private String halfId;
    private Settings settings;

    public String getHalfId() {
        return halfId;
    }

    public void setHalfId(String halfId) {
        this.halfId = halfId;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
