package my.study.sprintbootabc.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import my.study.sprintbootabc.dao.annotation.Mapper;
import my.study.sprintbootabc.mapper.sql.RoomSqlProvider;
import my.study.sprintbootabc.model.Room;

@Mapper
public interface RoomMapper extends BaseMapper<Room>{
	@InsertProvider(method = "insert", type = RoomSqlProvider.class)
	int insert(Room record) throws Exception;

	@UpdateProvider(method = "update", type = RoomSqlProvider.class)
	int update(Room record) throws Exception;

	@UpdateProvider(method = "updateSelective", type = RoomSqlProvider.class)
	int updateSelective(Room record) throws Exception;

	@DeleteProvider(method = "delete", type = RoomSqlProvider.class)
	int delete(Room rec) throws Exception;

	@SelectProvider(method = "selectById", type = RoomSqlProvider.class)
	@Results(id="RoomResultMap", value ={
			@Result(id=true, property="id",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER)
	})
	Room selectById(Integer id) throws Exception;
}