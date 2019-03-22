package com.example.demo.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class ImageUtil {

	public static void saveImg(int id,MultipartFile image,String folder,HttpServletRequest request) throws IllegalStateException, IOException {
		File imageFolder = new File(request.getServletContext().getRealPath(folder));
		File file = new File(imageFolder,id+".jpg");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		//将文件写到该路径下
		image.transferTo(file);
		//将文件转换成jpg格式
		BufferedImage image2 = change2jpg(file);
		ImageIO.write(image2, "jpg", file);					
	}
		
	public static BufferedImage change2jpg(File f) {
        try {
            Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
            return img;
        } catch (InterruptedException e) {          
            e.printStackTrace();
            return null;
        }
    }
	
	public static void resizeImage(File srcFile, int width,int height, File destFile) {
        try {
            if(!destFile.getParentFile().exists())
                destFile.getParentFile().mkdirs();
            Image i = ImageIO.read(srcFile);
            i = resizeImage(i, width, height);
            ImageIO.write((RenderedImage) i, "jpg", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
    public static Image resizeImage(Image srcImage, int width, int height) {
        try {
  
            BufferedImage buffImg = null;
            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
  
            return buffImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
