package my.study.exceldemo.read;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import my.study.exceldemo.FileUtil;

public class ReadXls{	
	private CellReader cellReader;
	public CellReader getCellReader() {
		return cellReader;
	}
	public void setCellReader(CellReader cellReader) {
		this.cellReader = cellReader;
	}
	
	public void readXls(String filePath, int sheetNum, int startRowNum, int startColNum, Integer maxColCount) throws Exception {
		InputStream file = FileUtil.loadFile(filePath);
		
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		
		
		try{
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetNum);
			int rowEnd = hssfSheet.getLastRowNum();
			for (int i = startRowNum; i <= rowEnd; i++) {
				HSSFRow row = hssfSheet.getRow(i);
				if (null == row)
					continue;
				
				int cellEnd = maxColCount == null ? row.getLastCellNum() : startColNum + maxColCount;
				

				List<HSSFCell> cells = new ArrayList<HSSFCell>();
				for (int k = startColNum; k < cellEnd ; k++) {
					HSSFCell cell = row.getCell(k);
					if (null == cell)
						continue;
					cells.add(cell);
					cellReader.readCell(cell);
				}
				if(cells != null && cells.size() > 0){
					cellReader.readRow(i, cells.toArray(new HSSFCell[0]));
				}
			}
			cellReader.finishRead();
		}finally{
			hssfWorkbook.close();
		}
		

	}
	
}


