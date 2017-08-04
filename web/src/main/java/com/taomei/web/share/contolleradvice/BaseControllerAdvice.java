package com.taomei.web.share.contolleradvice;

import com.taomei.dao.entities.ResultView;
import com.taomei.web.share.utils.ResultViewStatusUtil;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultView handleException(Exception e){
        return ResultViewUtil.error(500,e.getMessage());
    }
}
