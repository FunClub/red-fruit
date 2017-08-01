package com.taomei.service.utils;

/**
 * 图片变换的条件
 */
public enum ImageConditionUtil {
    /**
     * 心情图片条件
     */
    MOOD(80,80);
    private int width;
    private int height;


    ImageConditionUtil(int width, int height) {
        this.width = width;
        this.height = height;
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

}
