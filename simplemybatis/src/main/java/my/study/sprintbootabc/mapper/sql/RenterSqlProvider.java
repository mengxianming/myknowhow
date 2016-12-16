package my.study.sprintbootabc.mapper.sql;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import my.study.sprintbootabc.model.Renter;

public class RenterSqlProvider extends BaseSqlProvider<Renter>{
	
	public String selectByMap(Map<String, Object> params){
		SQL sql = new SQL().SELECT(StringUtils.join(getColumnNames(false), ","))
				.FROM(getModelMeta().getTableName())
				.WHERE("name=#{names[1]}")
				.AND()
				.WHERE("phone=#{phone.phoneNum}")
				.AND()
				.WHERE("age=${age}");
		return sql.toString();
	}
}
