package my.study.exceldemo.write;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.study.exceldemo.db.DBHelper;
import my.study.exceldemo.vo.Config;

public class DBContentProvider implements ContentProvider{
	private Config config;
	private List<String> excludeProps = new ArrayList<String>(Arrays.asList("class"));
	private DBHelper dbHelper;

	public DBContentProvider(Config config, String[] excludeProps){
		this.config = config;
		if(excludeProps != null){
			this.excludeProps.addAll(Arrays.asList(excludeProps));
		}
		this.dbHelper = new DBHelper(config);
		
	}
	
	public Object[][] getContents() {		
		List<?> beans = dbHelper.query(config.getWriteOpt().getSql());
		BeanListContentProvider wrapper = new BeanListContentProvider(beans, null);
		return wrapper.getContents();		
	}
	
}
