package com.taomei.dao.dtos.note;

/**
 * 查询一篇日志的dto
 */
public class SelectNoteDto {
    private String userId;
    private String noteId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
