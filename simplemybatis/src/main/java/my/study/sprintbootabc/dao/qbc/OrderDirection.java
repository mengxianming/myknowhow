package my.study.sprintbootabc.dao.qbc;
/**
 * 查询结果排序方向
 * @author admin
 *
 */
public enum OrderDirection{
	ASC("asc"),
	DESC("desc");
	private String dir;
	private OrderDirection(String dir){
		this.dir = dir;
	}
	public String getDir() {
		return dir;
	}		
}