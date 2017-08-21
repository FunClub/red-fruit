package com.taomei.dao.dtos.album;

import java.util.List;

/**
 * 上传相片时的相片数据
 */
public class ShowUploadPhotoDto {
    private String path;
    private int width;
    private int height;
    private int fontSize;
    private String name;
    private int zoomSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getZoomSize() {
        return zoomSize;
    }

    public void setZoomSize(int zoomSize) {
        this.zoomSize = zoomSize;
    }
}
