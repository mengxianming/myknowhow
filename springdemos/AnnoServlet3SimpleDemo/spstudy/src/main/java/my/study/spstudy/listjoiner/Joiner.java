package my.study.spstudy.listjoiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Joiner{
	public static enum JoinType{LEFT, INNER, RIGHT}
	
	ListJoiner leftLju;
	ListJoiner rightLju;
	JoinType joinType;
	
	
	Joiner(ListJoiner left, ListJoiner right, JoinType joinType){
		this.leftLju = left;
		this.rightLju = right;
		this.joinType = joinType;
	}
	
	ListJoiner on(String joinExpr){
		JoinExpression expr = new JoinExpression(joinExpr);		
				
		List<DataRow> newRows = new ArrayList<>();
		
		ListJoiner main = leftLju;
		ListJoiner target = rightLju;
		if(joinType == JoinType.RIGHT){
			main = rightLju;
			target = leftLju;
		}
		for(DataRow row : main.rows){
			boolean match = false;
			for(DataRow row2 : target.rows){
				Map<String, Object> context = new HashMap<>();
				add2Context(row, context);
				add2Context(row2, context);
				
				if(expr.eval(context)){
					match = true;
					DataRow newRow = new DataRow();
					newRow.cols.addAll(row.cols);
					newRow.cols.addAll(row2.cols);
					newRows.add(newRow);
				}				
			}
			
			//处理左连接、右连接的null情况
			if(!match && joinType != JoinType.INNER){
				DataRow newRow = new DataRow();
				newRow.cols.addAll(row.cols);
				for(int i = 0; i < target.alias.size(); i++){
					newRow.cols.add(null);
				}
				newRows.add(newRow);
			}
			
						
		}
		
		leftLju.alias.addAll(rightLju.alias);
		leftLju.rows = newRows;
		
		return leftLju;
	}
	
	private void add2Context(DataRow row, Map<String, Object> context) {
		for(DataCol col : row.cols){
			context.put(col.alia, col.bean);
		}		
	}
	
}