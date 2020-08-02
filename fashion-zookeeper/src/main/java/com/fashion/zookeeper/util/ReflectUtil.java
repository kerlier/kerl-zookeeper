package com.fashion.zookeeper.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ReflectUtil {
	
	
	public static void setValue(Object obj, Class<?> clazz, String filedName, Class<?> typeClass, Object value) {
		filedName = removeLine(filedName);
		String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[] { typeClass });
			method.invoke(obj, new Object[] { getClassTypeValue(typeClass, value) });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
	/**
	 * 通过class类型获取获取对应类型的值
	 * 
	 * @param typeClass class类型
	 * @param value     值
	 * @return Object
	 */
	private static Object getClassTypeValue(Class<?> typeClass, Object value) {
		if (typeClass == int.class || value instanceof Integer) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == short.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == byte.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == double.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == long.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == String.class) {
			if (null == value) {
				return "";
			}
			return value;
		} else if (typeClass == boolean.class) {
			if (null == value) {
				return true;
			}
			return value;
		} else if (typeClass == BigDecimal.class) {
			if (null == value) {
				return new BigDecimal(0);
			}
			return new BigDecimal(value + "");
		} else {
			return typeClass.cast(value);
		}
	}
 
	/**
	 * 处理字符串 如： abc_dex ---> abcDex
	 * 
	 * @param str
	 * @return
	 */
	public static String removeLine(String str) {
		if (null != str && str.contains("_")) {
			int i = str.indexOf("_");
			char ch = str.charAt(i + 1);
			char newCh = (ch + "").substring(0, 1).toUpperCase().toCharArray()[0];
			String newStr = str.replace(str.charAt(i + 1), newCh);
			String newStr2 = newStr.replace("_", "");
			return newStr2;
		}
		return str;
	}

}
