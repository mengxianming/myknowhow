package my.study.sprintbootabc.model.meta;

import org.apache.ibatis.type.JdbcType;

public class ColumnDef{
		private String fieldName;
		private String name;
		private Boolean isPK;
		private JdbcType jdbcType;
		
		public ColumnDef() {
			
		}
		
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Boolean getIsPK() {
			return isPK;
		}
		public void setIsPK(Boolean isPK) {
			this.isPK = isPK;
		}
		public JdbcType getJdbcType() {
			return jdbcType;
		}
		public void setJdbcType(JdbcType jdbcType) {
			this.jdbcType = jdbcType;
		}
		
		
	}