package my.study.exceldemo;

import org.apache.poi.ss.usermodel.Cell;

public class PrintCellReader extends AbstractCellReader {
	public void readCell(Cell cell) {
		System.out.print(getCellAsString(cell));
		if (cell.getColumnIndex() == cell.getRow().getLastCellNum() - 1) {
			System.out.println();
		} else {
			System.out.print("\t");
		}
	}

}