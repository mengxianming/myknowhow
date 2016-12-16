package my.study.sprintbootabc.dao.qbc;
public enum OP{
	LT("<"),
	LE("<="),
	EQ("="),
	NE("<>"),
	GT(">"),
	GE(">="),
	IN("IN"),
	ISNULL("IS NULL"),
	ISNOTNULL("IS NOT NULL"),
	LIKE("like")
	;
	private String op;
	private OP(String op){
		this.op = op;
	}
	public String getOp() {
		return op;
	}
	@Override
	public String toString() {
		return getOp();
	}		
}