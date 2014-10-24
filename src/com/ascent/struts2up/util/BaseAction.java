package com.ascent.struts2up.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public String callbackparam;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getCallbackparam() {
		return callbackparam;
	}

	public void setCallbackparam(String callbackparam) {
		this.callbackparam = callbackparam;
	}

	/**
	 * 返回字符串
	 * @param string
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public String returnString(String string,String charset) throws Exception {
		try {
			this.response.setContentType("text/plain");
			this.response.setCharacterEncoding(charset);
			PrintWriter writer= this.response.getWriter();
			if(callbackparam!=null){
				writer.write(callbackparam+"("+string+")");
			}else{
				writer.write(string);
			}
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return string;
	}
}
