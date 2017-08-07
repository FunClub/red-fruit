package com.taomei.web.share.contolleradvice;

import com.taomei.dao.dtos.login.LoginDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
/**
 * 用户相关操作切面
 */
@Aspect
@Component
public class UserAspect {
    private final HttpSession session;

    @Autowired
    public UserAspect(HttpSession session) {
        this.session = session;
    }


    /**
     * 从从session中取出userId并设置到目标方法
     * @param point
     * @return
     */
    @Around("@annotation(com.taomei.web.share.anotaions.SetUserId)")
    public Object setUserId(ProceedingJoinPoint point) throws Exception,Throwable {
        Object[] args=point.getArgs();
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto==null)throw new Exception("用户未登陆");
        args[0]=loginDto.getUserId();
        return point.proceed(args);
    }

    /**
     * 从从session中取出halfId并设置到目标方法
     * @param point
     * @return
     */
    @Around(value = "@annotation(com.taomei.web.share.anotaions.SetHalfId)")
    public Object setHalfId(ProceedingJoinPoint point) throws Exception,Throwable {
        Object[] args=point.getArgs();
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto==null)throw new Exception("用户未登陆");
        args[0]=loginDto.getHalf().getHalfId();
        return point.proceed(args);
    }

    /**
     * 从从session中取出halfId,userId并设置到目标方法
     * @param point
     * @return
     */
    @Around(value = "@annotation(com.taomei.web.share.anotaions.SetId)")
    public Object setId(ProceedingJoinPoint point) throws Exception,Throwable {
        Object[] args=point.getArgs();
        LoginDto loginDto = (LoginDto) session.getAttribute("user");
        if(loginDto==null)throw new Exception("用户未登陆");
        args[0]=loginDto.getUserId();
        args[1]=loginDto.getHalf().getHalfId();
        return point.proceed(args);
    }

}
