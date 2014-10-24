package com.ascent.struts2up.action;

import java.io.FileInputStream;
import java.io.OutputStream;
import com.ascent.struts2up.util.BaseAction;
import com.ascent.struts2up.util.Constant;

public class ServletAction extends BaseAction {
	
	public String filename;
	public String imgPath;
	public String mini;

	
	public void servletFile() throws Exception{
		String imagePath = Constant.getValue("imgPath")+imgPath+"/"+filename;
		if("1".equals(mini)){
			imagePath = Constant.getValue("imgPath")+imgPath+"/mini/"+filename;
		}	
		            
		 FileInputStream fis = new FileInputStream(imagePath);  
		 int size =fis.available(); //得到文件大小   
		 byte data[]=new byte[size];   
		 fis.read(data);  //读数据   
		 fis.close();   
		 response.setContentType("image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/x-png, image/pjpeg"); //设置返回的文件类型   
		 OutputStream os = response.getOutputStream();  
		 os.write(data);  
		 os.flush();  
		 os.close();      

		
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getImgPath() {
		return imgPath;
	}


	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	public String getMini() {
		return mini;
	}


	public void setMini(String mini) {
		this.mini = mini;
	}
	
}
