package my.study.exceldemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public  class AbstractCellReader implements CellReader{

	public void readCell(Cell cell) {
		
	}

	public void readRow(int rowIdx, Cell[] rowCells) {		
	}
	
	protected String getCellAsString(Cell cell) {
		switch (cell.getCellType()){
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字	    
			short format = cell.getCellStyle().getDataFormat();  
			SimpleDateFormat sdf = null;  
			if(format == 14 || format == 31 || format == 57 || format == 58){  
				//日期  
				sdf = new SimpleDateFormat("yyyy-MM-dd");  
			}else if (format == 20 || format == 32) {  
				//时间  
				sdf = new SimpleDateFormat("HH:mm");  
			}else{
				return num2Str( cell.getNumericCellValue());
			}
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue());  
			return sdf.format(date);  
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