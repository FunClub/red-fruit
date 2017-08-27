package com.taomei.service.share;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.taomei.dao.dtos.album.ShowUploadPhotoDto;
import com.taomei.service.share.utils.TimeUtil;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
     * 生成base64URL
     * @param code 等待加密的字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public String generateBase64Url(String code) throws UnsupportedEncodingException {
        byte[] b= UrlBase64.encode(code.getBytes("UTF-8"));
        String base64Url=new String(b,"UTF-8");
        StringBuilder sb = new StringBuilder(base64Url);

        /*去除点*/
        int index=sb.indexOf(".");
        if(index!=-1){
            sb=sb.delete(index,base64Url.length());
        }
        return sb.toString();
    }

    /**
     * 生成上传相片的预览
     * @param files 文件jihe
     * @param folder 文件夹
     * @return
     * @throws IOException
     */
    public List<ShowUploadPhotoDto> generateUpLoadPhotoDto(List<MultipartFile> files,String folder) throws IOException {
        List<ShowUploadPhotoDto> dtos = new ArrayList<>();
        for(MultipartFile file:files){
            ShowUploadPhotoDto dto = new ShowUploadPhotoDto();
            //生成图片的路径
            String path = generateImgPath(file,folder);

            //得到高度
            InputStream in = file.getInputStream();
            BufferedImage bi = ImageIO.read(in);
            int width = bi.getWidth();
            int height = bi.getHeight();

            //计算水印字体大小
            double orFontSize=15;//图片大小为300X400

            //如过图片过小就设置不缩放,水印字体大小为12否则就计算。
            if(height>=400&&width>=300 ||height>=300&&width>400){
                if(height>width){
                    double i = ((height-400)/88.0)*0.5;
                    orFontSize+=i;
                    i=((width-300)/66.0)*0.5;
                    orFontSize+=i;
                }else{
                    double i = ((width-400)/88.0)*0.5;
                    orFontSize+=i;
                    i=((height-300)/66.0)*0.5;
                    orFontSize+=i;
                }
                dto.setFontSize((int) orFontSize);
                dto.setZoomSize(calculateZoomSize(width,height));
            }else{
                dto.setFontSize(12);
                dto.setZoomSize(100);
            }

            dto.setPath(path);
            dto.setHeight(height);
            dto.setWidth(width);
            dto.setName(file.getOriginalFilename());
            dtos.add(dto);
        }
        return dtos;
    }

    private int calculateZoomSize(int width,int height){
        int p;
        if(height>width){
            p = (int) ((480.0/width)*100);
        }else{
            p= (int) ((380.0/height)*100);
        }
        return p;
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
