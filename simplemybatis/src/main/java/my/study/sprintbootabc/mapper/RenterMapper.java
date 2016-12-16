package my.study.sprintbootabc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import my.study.sprintbootabc.dao.annotation.Mapper;
import my.study.sprintbootabc.mapper.sql.RenterSqlProvider;
import my.study.sprintbootabc.model.Renter;

@Mapper
public interface RenterMapper extends BaseMapper<Renter>{
	@InsertProvider(method = "insert", type = RenterSqlProvider.class)
	int insert(Renter record) throws Exception;

	@UpdateProvider(method = "update", type = RenterSqlProvider.class)
	int update(Renter record) throws Exception;

	@UpdateProvider(method = "updateSelective", type = RenterSqlProvider.class)
	int updateSelective(Renter record) throws Exception;

	@DeleteProvider(method = "delete", type = RenterSqlProvider.class)
	int delete(Renter rec) throws Exception;

	@SelectProvider(method = "selectById", type = RenterSqlProvider.class)
	@Results(id="renterResultMap", value ={
			@Result(id=true, property="id",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER)
	})
	Renter selectById(Integer id) throws Exception;
	
	@SelectProvider(method = "selectByMap", type = RenterSqlProvider.class)
	@ResultMap("renterResultMap")
	List<Renter> selectByMap(Map<String, Object> params) throws Exception;
	
	
}