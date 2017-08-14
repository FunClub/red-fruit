package com.taomei.service.share.enums;

public enum FileFolder {
    MOOD("mood"),
    PROFILE("profile")
    ;
    private String folder;

    FileFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
