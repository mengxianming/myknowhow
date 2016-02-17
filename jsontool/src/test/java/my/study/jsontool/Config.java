package my.study.jsontool;

public class Config {	
	String excelFilePath;
	int sheetNum;
	int startRowNum;
	int startColNum;
	Integer maxColCount;
	int mode; // 0: print, 1: sqlgen, 2: enumgen
	
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getStartColNum() {
		return startColNum;
	}
	public void setStartColNum(int startColNum) {
		this.startColNum = startColNum;
	}
	public Integer getMaxColCount() {
		return maxColCount;
	}
	public void setMaxColCount(Integer maxColCount) {
		this.maxColCount = maxColCount;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	

}
