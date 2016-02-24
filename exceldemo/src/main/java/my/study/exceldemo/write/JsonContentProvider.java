package my.study.exceldemo.write;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import my.study.jsontool.JsonUtil;

public class JsonContentProvider implements ContentProvider{
	ObjectMapper om = new ObjectMapper();

	private String json;
	private List<String> excludeProps = new ArrayList<String>(Arrays.asList("class"));

	public JsonContentProvider(String json, String[] excludeProps){
		this.json = json;
		if(excludeProps != null){
			this.excludeProps.addAll(Arrays.asList(excludeProps));
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object[][] getContents() {			
		Object listOrMap = JsonUtil.fromJson(json);
		List<Object> beans = null;
		if(listOrMap instanceof List){
			beans = (List) listOrMap;
		}else if(listOrMap instanceof List){
			beans = Arrays.asList(listOrMap);
		}else{
			return new Object[0][0];
		}
		
		BeanListContentProvider wrapper = new BeanListContentProvider(beans, null);
		return wrapper.getContents();
		
	}
	
}
