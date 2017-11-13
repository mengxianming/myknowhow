package my.study.spstudy.druid;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.SQLUtils.FormatOption;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DruidSqlParserTest{
	private ObjectMapper objectMapper= new ObjectMapper();
	
	@Test
	public void testParseSql() throws JsonProcessingException{		
        String sql = "select user from emp_table where id>2 and id <=10 and age=10 and (time0 between 0 and 1) and (status = 0 or deleted=1) ";
        String dbType = JdbcConstants.MYSQL;
 
        //格式化输出
        String result = SQLUtils.format(sql, dbType);
        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
 
        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {
 
            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            // MySqlASTVisitor visitor = new MySqlEvalVisitorImpl(); // new  MySqlOutputVisitor(new StringBuilder());
            stmt.accept(visitor);
            
            //获取表名称
            System.out.println("basicInfo:" + objectMapper.writeValueAsString(visitor));
          
        }
	}
	
	@Test
	public void testFormat() throws JsonProcessingException{	
		  String sql = "select * from A where id>? and id <=? and name=? and cdate=? and udate=?";
	 
	       List<Object> params = Arrays.asList(new Object[]{Integer.valueOf(1), Integer.valueOf(10), "meng", new Date(), new Timestamp(System.currentTimeMillis())});
			//格式化输出
	        String result = SQLUtils.format(sql, JdbcConstants.MYSQL, params, new FormatOption(true, false));
	        System.out.println(result);
	}
}