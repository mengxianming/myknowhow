package my.study.spstudy.vo;

public class Page {
	private Integer pageNo; // 1-base
	private Integer pageRecCount;
	private Integer totalRecCount;
	
	public Integer getOffset(){
		if(pageNo != null && pageRecCount != null){
			return (pageNo - 1 ) * pageRecCount;
		}
		return null;
	}
	
	public Integer getLimit(){
		return pageRecCount == null ? null : pageRecCount;
	}
	
	public int getTotalPages(){
		if(totalRecCount != null && pageRecCount != null){
			return totalRecCount == 0 ? 0 : (totalRecCount / pageRecCount) + 1;
		}
		return 0;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageRecCount() {
		return pageRecCount;
	}

	public void setPageRecCount(Integer pageRecCount) {
		this.pageRecCount = pageRecCount;
	}

	public Integer getTotalRecCount() {
		return totalRecCount;
	}

	public void setTotalRecCount(Integer totalRecCount) {
		this.totalRecCount = totalRecCount;
	}
	
	
	
	

}
