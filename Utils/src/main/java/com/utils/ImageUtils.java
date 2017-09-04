package com.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {
	public static void imageCut(String url, int destWide, int destHeight ,OutputStream outputStream) throws Exception{
		File file = new File(url);
		imageCut(file, destWide, destHeight, outputStream);
	}
	public static void imageCut(File file, int destWide, int destHeight ,OutputStream outputStream) throws Exception{
		
		ImageInputStream stream = ImageIO.createImageInputStream(new FileInputStream(file));
		Iterator<ImageReader> iterator = ImageIO.getImageReaders(new FileImageInputStream(file));
		ImageReader imageReader = iterator.next();
		imageReader.setInput(stream, true);
		// 获取原来大小
		int oldWidth = imageReader.getWidth(0);
		int oldHeight = imageReader.getHeight(0);
		String formatName = imageReader.getFormatName();
		//左上角坐标s
		int x = (oldWidth - destWide)/2;
		int y = (oldHeight - destHeight)/2;
		// 设置裁剪区域
		Rectangle rectangle = new Rectangle(x, y, destWide, destHeight);
		ImageReadParam param = imageReader.getDefaultReadParam();
		param.setSourceRegion(rectangle);
		BufferedImage bufferedImage2 = imageReader.read(0, param);
		// 生成新图片
		ImageIO.write(bufferedImage2, formatName, outputStream);
		
	}
	public static void imageCut(InputStream inputStream, String formatName, int destWide, int destHeight ,OutputStream outputStream) throws Exception{
		ImageInputStream stream = ImageIO.createImageInputStream(inputStream);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(formatName);
		ImageReader imageReader = iterator.next();
		imageReader.setInput(stream, true);
		// 获取原来大小
		int oldWidth = imageReader.getWidth(0);
		int oldHeight = imageReader.getHeight(0);
		//左上角坐标s
		int x = (oldWidth - destWide)/2;
		int y = (oldHeight - destHeight)/2;
		// 设置裁剪区域
		Rectangle rectangle = new Rectangle(x, y, destWide, destHeight);
		ImageReadParam param = imageReader.getDefaultReadParam();
		param.setSourceRegion(rectangle);
		BufferedImage bufferedImage2 = imageReader.read(0, param);
		// 生成新图片
		ImageIO.write(bufferedImage2, formatName, outputStream);
		
	}
	/*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
   public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        
        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//设置缩放目标图片模板
        
        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   /*
     * 图片按比率缩放
     * size为文件大小
     */
    public static void zoomImage(String src,String dest,Integer size) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);
        
        long fileSize = srcFile.length();
        if(fileSize < size * 1024)   //文件大于size k时，才进行缩放,注意：size以K为单位
            return;
        
        Double rate = (size * 1024 * 0.5) / fileSize; // 获取长宽缩放比例
        
        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), bufImg.SCALE_SMOOTH);
            
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
	 * 比例缩放
	 * @param srcImageFile
	 * @param result
	 * @param scale 缩放比例
	 * @param flag true为放大，false为缩小
	 */
    public static void scale(String srcImageFile, String result, int scale, boolean flag)  
    {  
        try  
        {  
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件  
            int width = src.getWidth(); // 得到源图宽  
            int height = src.getHeight(); // 得到源图长  
            if (flag)  
            {  
                // 放大  
                width = width * scale;  
                height = height * scale;  
            }  
            else  
            {  
                // 缩小  
                width = width / scale;  
                height = height / scale;  
            }  
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
