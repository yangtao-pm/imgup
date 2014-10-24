package com.ascent.struts2up.util;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class Img {

	public static String receiveImg(String imgName, String imgPath,File source, float width,float height)
			throws Exception {
		String flag = null;
		String newimgName = null;
		if(!isPicture(imgName)){
			return flag = "不是图片";
		}
		
		try {
			File f = new File(Constant.getValue("imgPath")+imgPath);
			// 文件夹是否存在，不存在创建文件夹
			if (!f.exists()) {
				f.mkdirs();
			}

			f = new File(Constant.getValue("imgPath")+imgPath+"/mini");
			if (!f.exists()) {
				f.mkdirs();
			}

			String newName = getUUID();
			String tmpName = imgName.substring(imgName.lastIndexOf("."), imgName.length());
			newimgName = newName+tmpName;
			FileInputStream fis = new FileInputStream(source);
			
//			FileOutputStream fos = new FileOutputStream(imgPath+"/"+imgName);
			FileOutputStream fos = new FileOutputStream(Constant.getValue("imgPath")+imgPath+"/"+newimgName);
			
			FileChannel src = fis.getChannel();
			
			FileChannel tar = fos.getChannel();
			
			src.transferTo(0, fis.available(), tar);
			
			tar.close();
			
			src.close();
			
			fos.close();
			
			fis.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(width>0 || height>0){
			ImageMini.createThumbnail(Constant.getValue("imgPath")+imgPath+"/"+newimgName, Constant.getValue("imgPath")+imgPath+"/mini/"+newimgName, width, height);
		}else{
			File yfile = new File(Constant.getValue("imgPath")+imgPath+"/"+newimgName);
			File xfile = new File(Constant.getValue("imgPath")+imgPath+"/mini/"+newimgName);
			copyFile(yfile, xfile);
		}
		
		flag = Constant.getValue("System.URL")+"?imgPath="+imgPath+"&filename="+newimgName;
		return flag;
	}

	public static boolean isPicture(String pImgeFlag)
			throws Exception {
		
		if (pImgeFlag==null) {
			return false;
		}
		
		// 获得文件后缀
		String tmpName = pImgeFlag.substring(pImgeFlag.lastIndexOf(".") + 1, pImgeFlag
				.length());
		// 声明图片后缀名数
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
				{ "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
				{ "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			
			if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	// 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
        	inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
        	 outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
	
  public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }
  

}
