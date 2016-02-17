package my.study.exceldemo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public  class AbstractCellReader implements CellReader{

	public void readCell(Cell cell) {
		
	}

	public void readRow(int rowIdx, Cell[] rowCells) {		
	}
	
	protected String getCellAsString(Cell cell) {
		switch (cell.getCellType())
	    {
	        case HSSFCell.CELL_TYPE_NUMERIC: // 数字	        	
	        	return num2Str( cell.getNumericCellValue());
	        case HSSFCell.CELL_TYPE_STRING: // 字符串
	           return cell.getStringCellValue();
	        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
	        	return String.valueOf(cell.getBooleanCellValue());
	        case HSSFCell.CELL_TYPE_FORMULA: // 公式
	        	String value;
	        	try {
	        		value = num2Str( cell.getNumericCellValue());
	        	} catch (IllegalStateException e) {
	        		value = String.valueOf(cell.getRichStringCellValue());
	        	}
	            return value;
	        case HSSFCell.CELL_TYPE_BLANK: // 空值
	        case HSSFCell.CELL_TYPE_ERROR: // 故障
	        default:
	               return "";
	    }
	}

	private String num2Str(Double v) {
    	return v - v.longValue() == 0 ? String.valueOf(v.longValue()) : v.toString();
	}
	public void finishRead() {
		
	}
}