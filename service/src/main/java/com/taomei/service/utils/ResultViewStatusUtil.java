package com.taomei.service.utils;

/**
 * 返回给浏览器的统一数据对象ResultView的状态工具类
 */
public enum  ResultViewStatusUtil {
    SUCCESS(200,"请求成功"),
    NOT_FOUND(404,"未找到数据"),
    EXISTS(400,"数据已存在");

    private String message;
    private Integer code;

    ResultViewStatusUtil( Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
