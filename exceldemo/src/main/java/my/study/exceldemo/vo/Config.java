package my.study.exceldemo.vo;

public class Config {	
	String excelFilePath;
	Integer sheetNum;
	Integer startRowNum;
	Integer startColNum;
	Integer maxColCount;
	Integer mode; // 0: print, 1: sqlgen, 2: enumgen, 3:jsongen
	
	SqlGenOpt sqlGenOpt;
	EnumGenOpt enumGenOpt;
	XlsWriteOpt writeOpt;
	
	
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public Integer getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(Integer sheetNum) {
		this.sheetNum = sheetNum;
	}
	public Integer getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(Integer startRowNum) {
		this.startRowNum = startRowNum;
	}
	public Integer getStartColNum() {
		return startColNum;
	}
	public void setStartColNum(Integer startColNum) {
		this.startColNum = startColNum;
	}
	public Integer getMaxColCount() {
		return maxColCount;
	}
	public void setMaxColCount(Integer maxColCount) {
		this.maxColCount = maxColCount;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public SqlGenOpt getSqlGenOpt() {
		return sqlGenOpt;
	}
	public void setSqlGenOpt(SqlGenOpt sqlGenOpt) {
		this.sqlGenOpt = sqlGenOpt;
	}
	public EnumGenOpt getEnumGenOpt() {
		return enumGenOpt;
	}
	public void setEnumGenOpt(EnumGenOpt enumGenOpt) {
		this.enumGenOpt = enumGenOpt;
	}
	
	public XlsWriteOpt getWriteOpt() {
		return writeOpt;
	}
	public void setWriteOpt(XlsWriteOpt writeOpt) {
		this.writeOpt = writeOpt;
	}
	
	

}
