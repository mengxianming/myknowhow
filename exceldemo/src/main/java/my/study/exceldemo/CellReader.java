package my.study.exceldemo;

import org.apache.poi.ss.usermodel.Cell;

public  interface CellReader{
	void readCell(Cell cell);
	void readRow(int rowIdx, Cell[] rowCells);
	void finishRead();
}

