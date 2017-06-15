package cn.yq.springmvc.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
protected Log log=LogFactory.getLog(this.getClass());
	
	@Autowired
	private  HttpServletRequest request;  
	protected Map<String,String> parameters(){
		Map<String, String> parameters=new HashMap<>();
	
		Map<String,String[]> params=request.getParameterMap();
		if(params!=null){
			for(Map.Entry<String,String[]> entry:params.entrySet()){
				String[] value=entry.getValue();
				if(value!=null && value.length!=0){
					parameters.put(entry.getKey(), value[0]);
				}
			}
		}
		return parameters;
		
	}
}
