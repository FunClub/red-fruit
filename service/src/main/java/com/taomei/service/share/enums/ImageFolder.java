package com.taomei.service.share.enums;

public enum ImageFolder {
    MOOD("mood"),
    PROFILE("profile")
    ;
    private String folder;

    ImageFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
