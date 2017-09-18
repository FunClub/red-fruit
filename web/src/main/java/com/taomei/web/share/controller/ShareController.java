package com.taomei.web.share.controller;

import com.taomei.dao.dtos.share.card.SelectCardDto;
import com.taomei.dao.entities.Attention;
import com.taomei.service.share.service.ImageService;
import com.taomei.service.share.service.ShareService;
import com.taomei.web.share.anotaions.SetUserId;
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
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 共享控制器
 */
@RestController
@RequestMapping("/share")
public class ShareController {
    private final ImageService imageService;
    private final ShareService shareService;

    @Autowired
    public ShareController(ImageService imageService, ShareService shareService) {
        this.imageService = imageService;
        this.shareService = shareService;
    }


    /**
     * 查询被关注人信息
     * @param userId 关注人id
     * @return 统一数据对象
     */
    @GetMapping("/attention")
    @SetUserId
    public ResultView selectAttentionUsers(String userId){
        return ResultViewUtil.success(shareService.selectAttentionUsers(userId));
    }
    /**
     * 取消关注
     * @param userId 关注人id
     * @param attentionUserId 被关注人id
     * @return 统一数据对象
     */
    @DeleteMapping("/{attentionUserId}/attention")
    @SetUserId
    public ResultView deleteAttention(String userId, @PathVariable("attentionUserId") String attentionUserId){
        SelectCardDto dto = new SelectCardDto();
        dto.setCardUserId(attentionUserId);
        dto.setUserId(userId);
        return ResultViewUtil.success(shareService.deleteAttention(dto));
    }
    /**
     * 添加关注
     * @param userId 用户id
     * @param attentionUserId 被关注用户的id
     * @return 统一数据对象
     */
    @PostMapping("/{attentionUserId}/attention")
    @SetUserId
    public ResultView addAttention(String userId,@PathVariable("attentionUserId") String attentionUserId){
        Attention attention = new Attention();
        attention.setUserId(userId);
        attention.setAttentionUserId(attentionUserId);
        return ResultViewUtil.success(shareService.addAttention(attention));
    }
    /**
     * 查询用户card信息
     * @param cardUserId 被查询的用户id
     * @return 统一数据对象
     */
    @GetMapping("/{userId}/card")
    @SetUserId
    public ResultView selectShowCardUserDto(String userId,@PathVariable("userId") String cardUserId){
        SelectCardDto dto = new SelectCardDto();
        dto.setCardUserId(cardUserId);
        dto.setUserId(userId);
        return ResultViewUtil.success(shareService.selectCardUser(dto));
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
        return ResultViewUtil.success(imageService.generateImgPaths(imgs,folder));
    }
    @PatchMapping("img")
    public ResultView delete(@RequestBody List<String> moodImgs) throws Exception {
        imageService.deleteImgs(moodImgs);
     return ResultViewUtil.success(true);
    }

    /**
     * 生成base64URL
     * @param code 等待加密的字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("base64-url")
    public ResultView generateBase64Url(String code) throws UnsupportedEncodingException {
       return ResultViewUtil.success(imageService.generateBase64Url(code));
    }
}
