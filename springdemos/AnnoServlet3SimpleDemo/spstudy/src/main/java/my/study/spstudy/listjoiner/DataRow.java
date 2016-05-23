package my.study.spstudy.listjoiner;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

public class DataRow{
	List<DataCol> cols = new ArrayList<>();
	static DataRow newRow(DataCol... cols){
		DataRow row = new DataRow();
		for(DataCol col : cols){
			row.cols.add(col);
		}
		return row;
	}

	Object getProperty(String alia, String propName){
		for(DataCol col : cols){
			if(col.alia.equals(alia)){
				try {
					return PropertyUtils.getProperty(col.bean, propName);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		throw new IllegalArgumentException("alia不存在。alia=" + alia);
	}

	DataCol getDataCol(String alia){
		for(DataCol col : cols){
			if(col != null && col.alia.equals(alia)){
				return col;
			}
		}
		return null;
	}
}