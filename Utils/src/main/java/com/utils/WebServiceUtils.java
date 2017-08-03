package com.utils;

import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

public class WebServiceUtils {
	public static String doService(String endPoint,String namespace,String methodname,Map<String, String> params,Map<String, String> headers){
		try{
			Service service = new Service();  
	        Call call = (Call)service.createCall(); 
	        call.setTargetEndpointAddress( new  java.net.URL(endPoint));              
	        call.setOperationName( new QName(namespace,methodname));  
	        SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement(namespace , "CustomSoapHeader" );
	        soapHeaderElement.setNamespaceURI( namespace );
	        // headers
	        if(headers!=null && !headers.isEmpty()){
	        	for(String key : headers.keySet()){
		        	soapHeaderElement.addChildElement( key ).setValue( headers.get(key) );
		        }
	        }
	        
	        call.addHeader(soapHeaderElement);
	        // params
	        for(String key : params.keySet()){
	        	call.addParameter(key ,org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);  
	        }
	        // 
	        call.setUseSOAPAction( true); 
	         
	        call.setReturnType(org.apache.axis.Constants.XSD_STRING);    
	        // 
	        call.setSOAPActionURI(namespace+methodname); 
	        String result;
	        Object[] infoMap = params.values().toArray();
	        result = (String)call.invoke(new QName(namespace,methodname),infoMap);
	        System.out.println(result);
	        return result;
	    } catch(Exception e){ 
	        e.printStackTrace(); 
	        System. out.println("调用webservice失败!" );  
	        return null;
	    }
	}
}
