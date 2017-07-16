package com.taomei.dao.entities;

/**
 * 返回给浏览器的统一数据对象
 * @param <T>   真正数据
 */
public class ResultView<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 附加消息
     */
    private String message;

    /**
     * 真正的数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
