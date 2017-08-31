package com.taomei.dao.dtos.note;

/**
 * 显示一个日志内容的dto
 */
public class ShowNoteDto extends ShowCatalogNoteDto {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
