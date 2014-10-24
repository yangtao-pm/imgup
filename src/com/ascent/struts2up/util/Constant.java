package com.ascent.struts2up.util;

import com.ascent.struts2up.util.impl.ConstantImpl;


public abstract class Constant {
	private static final Constant constant = new ConstantImpl();
	public static Object getValue(String key){
		return constant.getObjectByKey(key);
	}
	protected abstract Object getObjectByKey(String key);
}
