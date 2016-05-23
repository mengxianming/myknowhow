package my.study.spstudy.listjoiner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.study.spstudy.listjoiner.Joiner.JoinType;

public class ListJoiner{
	Set<String> alias = new HashSet<String>();
	List<DataRow> rows = new ArrayList<>();

	public static ListJoiner init(List<?> list, String alia){
		ListJoiner ut = new ListJoiner();
		for(Object bean : list){
			ut.rows.add(DataRow.newRow(DataCol.newDataCol(alia, bean)));
		}
		ut.alias.add(alia);
		return ut;
	}

	public Joiner leftJoin(List<?> list, String alia){
		return doJoin(list, alia, JoinType.LEFT);
	}	
	
	public Joiner innerJoin(List<?> list, String alia){
		return doJoin(list, alia, JoinType.INNER);
	}
	
	public Joiner rightJoin(List<?> list, String alia){
		return doJoin(list, alia, JoinType.RIGHT);
	}
	
	private Joiner doJoin(List<?> list, String alia, JoinType joinType){
		if(alias.contains(alia)){
			throw new IllegalArgumentException("alia已经存在，不能使用该alia。alia=" + alia);
		}

		ListJoiner ut2 = init(list, alia);
		return new Joiner(this, ut2, joinType);
	}	
	
	public Set<String> getAlias() {
		return alias;
	}
	
	public List<DataRow> getRows() {
		return rows;
	}

	public void doBeanMerge(BeanMerger beanMerger){
		try {
			for(DataRow dr : this.rows){
				beanMerger.mergeDataRow(dr);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	



}