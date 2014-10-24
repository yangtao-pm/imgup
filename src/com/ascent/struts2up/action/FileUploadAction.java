package com.ascent.struts2up.action;

import java.io.File;

import com.ascent.struts2up.util.BaseAction;
import com.ascent.struts2up.util.Img;

@SuppressWarnings("serial")
public class FileUploadAction extends BaseAction {

	private File source;
	
	private String sourceFileName;
	
	private String basePath;
	
	private float width;
	
	private float height;
	

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public void uploadFile() throws Exception{
        String flag = null;
		try {
			flag = Img.receiveImg(this.getSourceFileName(), basePath,this.source, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer re = new StringBuffer();
		if(flag!=null && flag.equals("不是图片")){
			re.append("{ \"status\": { \"code\": \"1\",\"msg\": \"失败\"},");
			re.append("\"data\": [{\"imgPath\": \"\"}]}");
		}else{
			re.append("{ \"status\": { \"code\": \"0\",\"msg\": \"成功\"},");
			re.append("\"data\": [{\"imgPath\": \""+flag+"\",\"miniImgPath\": \""+flag+"&mini=1\"}]}");
		}
//		response.getWriter().write(re.toString());
		returnString(re.toString(),"UTF-8");
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}
