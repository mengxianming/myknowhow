package my.study.sprintbootabc.dao.qbc;

public class ConditionAndRelation{
	private Condition[] conditions;
	private String andOr;
	private String innerAndOr;
	public static ConditionAndRelation and(Condition... conditions){
		ConditionAndRelation ins = new ConditionAndRelation();
		ins.conditions = conditions;
		ins.andOr = "AND";
		return ins;
	}
	public static ConditionAndRelation andWithOrs(Condition... conditions){
		ConditionAndRelation ins = new ConditionAndRelation();
		ins.conditions = conditions;
		ins.andOr = "AND";
		ins.setInnerAndOr("OR");
		return ins;
	}
	public static ConditionAndRelation or(Condition... conditions){
		ConditionAndRelation ins = new ConditionAndRelation();
		ins.conditions = conditions;
		ins.andOr = "OR";
		return ins;
	}
	public String getAndOr() {
		return andOr;
	}
	public Condition[] getConditions() {
		return conditions;
	}

	public void setInnerAndOr(String innerAndOr) {
		this.innerAndOr = innerAndOr;
	}
	public String getInnerAndOr(){
		return innerAndOr == null ? "AND" : innerAndOr;
	}
}