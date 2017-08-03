package com.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
	
	/**
	 * 如果target属性为空不复制
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void copyProperties(Object source, Object target) throws Exception {  
	    Class<?> actualEditable = target.getClass();  
	    BeanInfo beanInfo = Introspector.getBeanInfo(actualEditable);
	    PropertyDescriptor[] targetPds = beanInfo.getPropertyDescriptors();  
	    for (PropertyDescriptor targetPd : targetPds) {  
	      if (targetPd.getWriteMethod() != null) {  
	        PropertyDescriptor sourcePd = new PropertyDescriptor(targetPd.getName(), source.getClass()); 
	        if (sourcePd != null && sourcePd.getReadMethod() != null) {  
	          try {  
	            Method readMethod = sourcePd.getReadMethod();  
	            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {  
	              readMethod.setAccessible(true);  
	            }  
	            Object value = readMethod.invoke(source);  
	            // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等  
	            if (value != null) {  
	              Method writeMethod = targetPd.getWriteMethod();  
	              if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {  
	                writeMethod.setAccessible(true);  
	              }  
	              writeMethod.invoke(target, value);  
	            }  
	          } catch (Throwable ex) {  
	            throw new RuntimeException("Could not copy properties from source to target", ex);  
	          }  
	        }  
	      }  
	    }  
	  }  
	
	/**
	 * obj to map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> ConvertObjToMap(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}
}
