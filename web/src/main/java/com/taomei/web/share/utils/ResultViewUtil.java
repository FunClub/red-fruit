package com.taomei.web.share.utils;

import com.taomei.dao.entities.ResultView;

/**
 * 生成展示给前端的数据工具类
 */
public class ResultViewUtil {

    /**
     *
     * @param data 数据
     * @return 返回给浏览器的统一数据对象
     */
    public static ResultView<Object> success(Object data){
        ResultView<Object> resultView=new ResultView<>();
        resultView.setCode(200);
        resultView.setMessage("请求处理成功");
        resultView.setData(data);
        return resultView;
    }

    /**
     *
     * @param code 错误消息状态码
     * @param message 错误消息
     * @return 返回给浏览器的统一数据对象
     */
    public static ResultView<Object> error(Integer code,String message){
        ResultView<Object> resultView=new ResultView<>();
        resultView.setCode(code);
        resultView.setMessage(message);
        return resultView;
    }
}
