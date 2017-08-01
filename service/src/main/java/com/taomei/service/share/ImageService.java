package com.taomei.service.share;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.utils.ResultViewUtil;
import com.taomei.service.utils.TimeUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

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
    private static final String MOOD_BUCKET_PATH ="mood/";
    public static final String MOOD__BUCKET_NAME ="red-fruit";
    private OSSClient client;
    public ImageService() {
        client =new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 删除图片
     * @param bucketName 仓库名
     * @param imgPath 路片路径集合
     * @return 统一对象
     */
   public void deleteMoodImg(String bucketName, List<String> imgPath){
       for(String path:imgPath){
           client.deleteObject(bucketName,path);
       }
   }


    /**
     * 上传心情图片并获得路径
     * @param files 上传的文件
     * @return 心情图片地址集合
     * @throws IOException 文件IO异常
     */
    public List<String> generateMoodPath(List<MultipartFile> files) throws IOException {
        List<String> imgPath = new ArrayList<>();
        ObjectMetadata meta = new ObjectMetadata();
        for(MultipartFile file:files){
            InputStream inputStream = file.getInputStream();
            String imageName =MOOD_BUCKET_PATH+ TimeUtil.getFileTime()+file.getOriginalFilename();
            meta.setContentLength(file.getBytes().length);
            client.putObject(MOOD__BUCKET_NAME,imageName,inputStream,meta);
            imgPath.add(imageName);
        }
        return imgPath;
    }

}
