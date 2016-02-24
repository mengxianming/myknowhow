package my.study.exceldemo.write;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanListContentProvider implements ContentProvider{

	private List<?> beans;
	private List<String> excludeProps = new ArrayList<String>(Arrays.asList("class"));

	public BeanListContentProvider(List<?> beans, String[] excludeProps){
		this.beans = beans;
		if(excludeProps != null){
			this.excludeProps.addAll(Arrays.asList(excludeProps));
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object[][] getContents() {
		Object[][] res = new Object[0][0];
		if(beans == null || beans.size() == 0){
			return res;
		}
		
		res = new Object[beans.size() + 1][0];
		boolean headerProcessed = false;
		try{
			for(int i = 0; i < beans.size(); i++){
				Object bean = beans.get(i);
							
				Map<?, ?> map;
				if(bean instanceof Map){
					map = (Map<?, ?>) bean;
				}else{
					map = PropertyUtils.describe(bean);
				}
				
				LinkedHashMap propMap = new LinkedHashMap(map);
				for(String exc : excludeProps){
					propMap.remove(exc);
				}
				
				if(!headerProcessed){
					res[0] = propMap.keySet().toArray();
					headerProcessed = true;
				}
				
				
				Object[] row = new Object[propMap.size()];	
				int j = 0;
				for(Object header : res[0]){					
					row[j++] = propMap.get(header);					
				}		
				res[i + 1] = row;
				
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		return res;
	}
	
}
