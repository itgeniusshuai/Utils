package com.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
}
