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
		// ��ȡԭ����С
		int oldWidth = imageReader.getWidth(0);
		int oldHeight = imageReader.getHeight(0);
		String formatName = imageReader.getFormatName();
		//���Ͻ�����s
		int x = (oldWidth - destWide)/2;
		int y = (oldHeight - destHeight)/2;
		// ���òü�����
		Rectangle rectangle = new Rectangle(x, y, destWide, destHeight);
		ImageReadParam param = imageReader.getDefaultReadParam();
		param.setSourceRegion(rectangle);
		BufferedImage bufferedImage2 = imageReader.read(0, param);
		// ������ͼƬ
		ImageIO.write(bufferedImage2, formatName, outputStream);
		
	}
	public static void imageCut(InputStream inputStream, String formatName, int destWide, int destHeight ,OutputStream outputStream) throws Exception{
		ImageInputStream stream = ImageIO.createImageInputStream(inputStream);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(formatName);
		ImageReader imageReader = iterator.next();
		imageReader.setInput(stream, true);
		// ��ȡԭ����С
		int oldWidth = imageReader.getWidth(0);
		int oldHeight = imageReader.getHeight(0);
		//���Ͻ�����s
		int x = (oldWidth - destWide)/2;
		int y = (oldHeight - destHeight)/2;
		// ���òü�����
		Rectangle rectangle = new Rectangle(x, y, destWide, destHeight);
		ImageReadParam param = imageReader.getDefaultReadParam();
		param.setSourceRegion(rectangle);
		BufferedImage bufferedImage2 = imageReader.read(0, param);
		// ������ͼƬ
		ImageIO.write(bufferedImage2, formatName, outputStream);
		
	}
	/*
     * ͼƬ����,w��hΪ���ŵ�Ŀ���Ⱥ͸߶�
     * srcΪԴ�ļ�Ŀ¼��destΪ���ź󱣴�Ŀ¼
     */
   public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        
        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //��ȡͼƬ
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);//��������Ŀ��ͼƬģ��
        
        wr=w*1.0/bufImg.getWidth();     //��ȡ���ű���
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile); //д���������ͼƬ
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   /*
     * ͼƬ����������
     * sizeΪ�ļ���С
     */
    public static void zoomImage(String src,String dest,Integer size) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);
        
        long fileSize = srcFile.length();
        if(fileSize < size * 1024)   //�ļ�����size kʱ���Ž�������,ע�⣺size��KΪ��λ
            return;
        
        Double rate = (size * 1024 * 0.5) / fileSize; // ��ȡ�������ű���
        
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
	 * ��������
	 * @param srcImageFile
	 * @param result
	 * @param scale ���ű���
	 * @param flag trueΪ�Ŵ�falseΪ��С
	 */
    public static void scale(String srcImageFile, String result, int scale, boolean flag)  
    {  
        try  
        {  
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�  
            int width = src.getWidth(); // �õ�Դͼ��  
            int height = src.getHeight(); // �õ�Դͼ��  
            if (flag)  
            {  
                // �Ŵ�  
                width = width * scale;  
                height = height * scale;  
            }  
            else  
            {  
                // ��С  
                width = width / scale;  
                height = height / scale;  
            }  
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // ������С���ͼ  
            g.dispose();  
            ImageIO.write(tag, "JPEG", new File(result));// ������ļ���  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
