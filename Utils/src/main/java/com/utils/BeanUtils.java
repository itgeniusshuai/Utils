package com.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BeanUtils {
	
	public static void main(String[] args) {
		Name name = new Name("b",3);
		Name name2 = new Name("a", null);
		try {
			copyProperties(name2, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name);
	}
	/**
	 * ���target����Ϊ�ղ�����
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
	            // �����ж�����value�Ƿ�Ϊ�� ��Ȼ����Ҳ�ܽ���һЩ����Ҫ��Ĵ��� �����ʱ��ʽת���ȵ�  
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
}
