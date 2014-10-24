package com.ascent.struts2up.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.ascent.struts2up.util.Constant;


public class ConstantImpl extends Constant {
	
	private static Properties properties;
	static{
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(ConstantImpl.class.getClassLoader().getResource("constant.properties").toURI())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getObjectByKey(String key) {
		return properties.getProperty(key);
	}

}
