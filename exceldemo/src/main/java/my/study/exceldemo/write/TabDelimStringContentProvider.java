package my.study.exceldemo.write;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TabDelimStringContentProvider implements ContentProvider{
	ObjectMapper om = new ObjectMapper();

	private String tabStr;
	private List<String> excludeProps = new ArrayList<String>(Arrays.asList("class"));

	public TabDelimStringContentProvider(String tabStr, String[] excludeProps){
		this.tabStr = tabStr;
		if(excludeProps != null){
			this.excludeProps.addAll(Arrays.asList(excludeProps));
		}
		
	}
	
	public Object[][] getContents() {			
		Object[][] contents;
		
		tabStr = tabStr.replaceAll("\r\n", "\n");
		String[] lines = tabStr.split("\n");
		contents = new Object[lines.length][0];
		for(int i = 0; i< lines.length; i++){
			if(lines[i] != null && lines[i].length() > 0){
				contents[i] = lines[i].split("\t");				
			}
		}
				
		return contents;
		
	}
	
}
