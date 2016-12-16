package my.study.sprintbootabc.dao.qbc;

public class OrderBy{
	private String field;
	private OrderDirection dir;
	public OrderBy(String field, OrderDirection dir){
		this.field = field;
		this.dir = dir;
	}
	public String getOrderBySql(){
		return field.concat(" ").concat(dir == null ? OrderDirection.ASC.getDir() : dir.getDir());
	}
}