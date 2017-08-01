package com.taomei.test;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtils1 {

    public BufferedImage zoomImage(String src) {

        BufferedImage result = null;

        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");

            }
            BufferedImage im = ImageIO.read(srcfile);

            /* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();

            //压缩计算
            float resizeTimes = 1;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/

            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);

            if(width>height){
                toHeight=80;
                toWidth=80*width/height;

            }else{
                toWidth=80;
                toHeight =80*height/width;
            }
            int cutSize=Math.abs(toWidth-toHeight)/2;
            /* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);
            if(width>height){
                result.getGraphics().drawImage(
                        im.getScaledInstance(toWidth, toHeight,
                                Image.SCALE_SMOOTH), cutSize, 0, null);
            }else{
                result.getGraphics().drawImage(
                        im.getScaledInstance(toWidth, toHeight,
                                Image.SCALE_SMOOTH), 0, cutSize, null);
            }



        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }

        return result;

    }

    public boolean writeHighQuality(BufferedImage im, String fileFullPath) {
        try {
                /*输出到文件流*/
            FileOutputStream newimage = new FileOutputStream(fileFullPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
                /* 压缩质量 */
            jep.setQuality(1, true);
            encoder.encode(im, jep);
               /*近JPEG编码*/
            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {

        String inputFoler = "E:\\WebStom Project\\img\\3.PNG" ;
         /*这儿填写你存放要缩小图片的文件夹全地址*/
        String outputFolder = "E:\\WebStom Project\\img\\3.1.PNG";
        /*这儿填写你转化后的图片存放的文件夹*/



        ImageUtils1 narrowImage = new ImageUtils1();
        narrowImage.writeHighQuality(narrowImage.zoomImage(inputFoler), outputFolder);

    }
}
