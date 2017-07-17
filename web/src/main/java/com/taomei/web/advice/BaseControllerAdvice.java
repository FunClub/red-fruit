package com.taomei.web.advice;

import com.taomei.dao.entities.ResultView;
import com.taomei.service.utils.ResultViewStatusUtil;
import com.taomei.service.utils.ResultViewUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultView handleException(Exception e){
        return ResultViewUtil.error(ResultViewStatusUtil.ERROR.getCode(),ResultViewStatusUtil.ERROR.getMessage()+":"+e.getMessage());
    }
}
