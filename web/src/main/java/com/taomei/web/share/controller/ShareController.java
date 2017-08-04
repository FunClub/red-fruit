package com.taomei.web.share.controller;

import com.taomei.service.share.ImageService;
import com.taomei.web.share.utils.ResultViewStatusUtil;
import com.taomei.web.share.utils.ResultViewUtil;
import com.taomei.dao.entities.ResultView;
import com.taomei.web.share.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * 共享控制器
 */
@RestController
@RequestMapping("/share")
public class ShareController {
    private final ImageService imageService;

    @Autowired
    public ShareController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * 向浏览器输入验证码
     * @param request javax.servlet.{@link HttpServletRequest}
     * @param response javax.servlet.{@link HttpServletResponse}
     */
    @GetMapping("/verificationCodeImg")
    public void createVerificationCodeImg(HttpServletRequest request, HttpServletResponse response){
        ValidateUtil.createVerificationCodeImg(request,response);
    }

    /**
     *
     * @param verificationCode 用户输入的验证码
     * @param session javax.servlet.http.{@link HttpSession}
     * @return
     */
    @GetMapping("/{verificationCode}/verificationCode")
    public ResultView validateVerificationCode(@PathVariable("verificationCode") String verificationCode, HttpSession session){
        String actualCode = session.getAttribute("verificationCode").toString();
        boolean result = actualCode.equals(verificationCode);
        return  ResultViewUtil.success(result);
    }

    @PutMapping("img/{folder}")
    public ResultView upload(@PathParam("imgs") List<MultipartFile> imgs, @PathVariable("folder") String folder) throws IOException {
        return ResultViewUtil.success(imageService.generateImgPath(imgs,folder));
    }
    @PatchMapping("img")
    public ResultView delete(@RequestBody List<String> moodImgs) throws Exception {

     return ResultViewUtil.success(imageService.deleteImg(moodImgs));

    }
}
