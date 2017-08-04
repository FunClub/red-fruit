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
    public static final String BUCKET_NAME ="red-fruit";
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
   public boolean deleteImg( List<String> imgPath) throws Exception {
       int i=0;
       for(String path:imgPath){
           client.deleteObject(BUCKET_NAME,path);
           i++;
       }
       if(i!=imgPath.size()){
            throw new Exception("删除图片失败");
       }
       return true;

   }


    /**
     * 上传图片并获得路径
     * @param files 上传的文件
     * @return 图片地址集合
     * @throws IOException 文件IO异常
     */
    public List<String> generateImgPath(List<MultipartFile> files,String folder) throws IOException {
        List<String> imgPath = new ArrayList<>();
        ObjectMetadata meta = new ObjectMetadata();
        for(MultipartFile file:files){
            InputStream inputStream = file.getInputStream();
            String imageName =folder+"/"+ TimeUtil.getFileTime()+file.getOriginalFilename();
            meta.setContentLength(file.getBytes().length);
            client.putObject(BUCKET_NAME,imageName,inputStream,meta);
            imgPath.add(imageName);
        }
        return imgPath;
    }

}
