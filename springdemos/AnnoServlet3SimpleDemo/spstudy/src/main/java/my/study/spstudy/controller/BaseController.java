package my.study.spstudy.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.type.TypeFactory;

import my.study.spstudy.util.JsonUtil;

public class BaseController {
	@Autowired
	HttpServletRequest request;
	
	protected Map<String, Object> toEntityModel(String url, Class<?> entityClass, Object entity, boolean isNew)
			throws Exception {
		Map<String, Object> md = new LinkedHashMap<String, Object>();
		md.put("url", url);
		md.put("name", entityClass.getSimpleName());
		if(entity == null){
			entity = entityClass.newInstance();
		}		
		
		String json = JsonUtil.toJson(entity);		
		Map<String, String> dataMap = JsonUtil.fromJson(json,
				TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class, String.class));
		if(isNew){
			dataMap.remove("id");
		}
		md.put("dataMap", dataMap);
		return md;
	}

}
