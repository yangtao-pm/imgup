package com.ascent.struts2up.util;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;

public class ImageMini {

	/**
	 * 创建图片缩略(等比缩放)
	 * 
	 * @param src
	 *            源图片文件完整路
	 * @param dist
	 *            目标图片文件完整路径
	 * @param width
	 *            缩放的宽
	 * @param height
	 *            缩放的高
	 */
	public static void createThumbnail(String src, String dist, float width,
			float height) {
		int newWidth = 0;
		int newHeight = 0;
		try {
			File srcfile = new File(src);
			if (!srcfile.exists()) {
				System.out.println("文件不存在");
				return;
			}
			BufferedImage image = ImageIO.read(srcfile);

			if(width>0 && height>0){
				newWidth = (int) width;
				newHeight = (int) height;
			}else if(width>0 && height<=0){
				double ratio = 1.0;
				if (image.getWidth() > width) {
					if (image.getHeight() > width) {
						ratio = image.getHeight() / width;
					} else {
						ratio = width / image.getWidth();
					}
				}
				
				// 计算新的图面宽度和高
				 newWidth = (int) width;
				 newHeight = (int) (image.getHeight()/ratio);
			}else if(height>0 && width<=0){
				double ratio = 1.0;
				if (image.getHeight() > height) {
					if (image.getWidth() > height) {
						ratio = image.getWidth() / height;
					} else {
						ratio = height / image.getHeight();
					}
				}
				
				// 计算新的图面宽度和高
				 newWidth = (int) (image.getWidth()/ratio);
				 newHeight = (int) height;
			}

			BufferedImage bfImage = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);
			bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream os = new FileOutputStream(dist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(bfImage);
			os.close();
			System.out.println("创建缩略图成功");
		} catch (Exception e) {
			System.out.println("创建缩略图发生异常" + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		createThumbnail("F:\\11111.jpg","D:\\好心情2(2).jpg",0,100);
	}

}
