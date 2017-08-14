package com.taomei.service.share;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.taomei.dao.entities.ResultView;

import com.taomei.service.share.utils.TimeUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片服务
 */
@Service
public class ImageService {
    private static final String ENDPOINT="http://oss-cn-shenzhen.aliyuncs.com";
    private static final String ACCESS_KEY_ID= "LTAIseZOgs0quMxr";
    private static final String ACCESS_KEY_SECRET = "duoh4gnCVAIu8gvDJ1p7vE1pg0ARuw";
    private static final String BUCKET_NAME ="red-fruit";
    private OSSClient client;
    public ImageService() {
        client =new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 删除图片
     *
     * @param imgPath 路片路径集合
     * @return 统一对象
     */
   public void deleteImgs( List<String> imgPath) throws Exception {
       for(String path:imgPath){
           deleteImg(path);
       }
   }

   public void deleteImg(String path) {
       client.deleteObject(BUCKET_NAME, path);
   }


    /**
     * 上传图片并获得路径
     * @param files 上传的文件
     * @return 图片地址集合
     * @throws IOException 文件IO异常
     */
    public List<String> generateImgPaths(List<MultipartFile> files,String folder) throws IOException {
        List<String> imgPath = new ArrayList<>();
        ObjectMetadata meta = new ObjectMetadata();
        for(MultipartFile file:files){
            imgPath.add(generateImgPath(file,folder));
        }
        return imgPath;
    }

    public String generateImgPath (MultipartFile file,String folder) throws IOException {
        ObjectMetadata meta = new ObjectMetadata();
        InputStream inputStream = file.getInputStream();
        String imageName =folder+"/"+ TimeUtil.getFileTime()+file.getOriginalFilename();
        meta.setContentLength(file.getBytes().length);
        client.putObject(BUCKET_NAME,imageName,inputStream,meta);
        return imageName;
    }

}
