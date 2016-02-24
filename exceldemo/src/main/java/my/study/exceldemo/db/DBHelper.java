package my.study.exceldemo.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.beanutils.RowSetDynaClass;

import my.study.exceldemo.vo.Config;  

public class DBHelper {  
	Config config;

	public DBHelper(Config config) {  
		this.config = config;
	}  

	public List<Object> query(String sql){
		Connection  conn = null;
		Statement stmt = null;
		try {  
			conn = getConnection();
			stmt = conn.createStatement();
			return result2List(stmt.executeQuery(sql));

		} catch (Exception e) {  
			throw new RuntimeException(e);
		} finally{
			close(stmt);
			close(conn);
		}
	}

	private void close(AutoCloseable stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	@SuppressWarnings("unchecked")
	private List<Object> result2List(ResultSet rs) throws SQLException {
		RowSetDynaClass rsdc = new RowSetDynaClass(rs);
		return rsdc.getRows();
	}

	private Connection getConnection() throws Exception {
		Class.forName(config.getWriteOpt().getJdbcDriver());//指定连接类型  
		return  DriverManager.getConnection(config.getWriteOpt().getJdbcUrl(),
				config.getWriteOpt().getJdbcUsername(), config.getWriteOpt().getJdbcPassword());//获取连接  
	}

}  