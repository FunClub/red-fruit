package com.taomei.test;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.awt.image.ConvolveOp;
import java.util.Iterator;

public class ImageUtils {

    public static void resize(File originalFile, File resizedFile,
                              int newWidth, float quality) throws IOException {

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance((newWidth * iWidth)
                    / iHeight, newWidth,Image.SCALE_AREA_AVERAGING);
        } else {
            resizedImage = i.getScaledInstance(newWidth,(newWidth * iHeight) / iWidth,
                     Image.SCALE_AREA_AVERAGING);
        }

        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage();

        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                80, BufferedImage.TYPE_INT_RGB);
        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics();

        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0,null);
        g.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "gif", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        // Write the jpeg to a file.
        FileOutputStream out = new FileOutputStream(resizedFile);

        // Encodes image as a JPEG data stream
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam param = encoder
                .getDefaultJPEGEncodeParam(bufferedImage);

        param.setQuality(quality, true);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);

        try {
            Iterator iterator = ImageIO.getImageReadersByFormatName("JPEG");
            ImageReader reader = (ImageReader)iterator.next();
            InputStream inputStream = new FileInputStream(resizedFile);
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            reader.setInput(iis, true);
            ImageReadParam param1 = reader.getDefaultReadParam();
            Rectangle rectangle = new Rectangle(0,0, 80, 80);
            param1.setSourceRegion(rectangle);
            BufferedImage bi = reader.read(0,param1);
            ImageIO.write(bi, "JPEG", resizedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Example usage

    public static void main(String[] args) throws IOException {
        File originalImage1= new File("E:\\WebStom Project\\img\\3.PNG");

        resize(originalImage1, new File("E:\\WebStom Project\\img\\3.1.jpg"),80, 1);

       /* resize(originalImage, new File("c:\\1207-1.jpg"),150, 1f);*/
    }
}