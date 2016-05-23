package my.study.spstudy.listjoiner;

public class DataCol{
	String alia;
	Object bean;
	public static DataCol newDataCol(String alia, Object data){
		DataCol ins = new DataCol();
		ins.alia = alia;
		ins.bean = data;
		return ins;
	}
}