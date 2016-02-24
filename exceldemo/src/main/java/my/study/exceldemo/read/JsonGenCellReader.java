package my.study.exceldemo.read;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import my.study.exceldemo.vo.Config;
import my.study.jsontool.JsonUtil;

public class JsonGenCellReader extends AbstractCellReader {
	private Config config;
	private PrintStream out;
	private String[] headers;
	private List<Object[]> rowVals = new ArrayList<Object[]>();
	private int maxColIdx;

	public JsonGenCellReader() {

	}
	public JsonGenCellReader(Config config, PrintStream out) {
		this.config = config;
		this.out = out;
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public PrintStream getOut() {
		return out;
	}
	public void setOut(PrintStream out) {
		this.out = out;
	}
		
	@Override
	public void readRow(int rowIdx, Cell[] rowCells) {		
		if(rowIdx == config.getStartRowNum() - 1){
			this.maxColIdx = rowCells.length - 1;	
			headers = new String[rowCells.length];
			for(int i = 0; i < rowCells.length; i++){
				Cell c = rowCells[i];
				headers[i] = getCellAsString(c);
			}					
			
		}else{
			List<Object> vals = new ArrayList<Object>();
			for(int i = 0; i < rowCells.length && i <= maxColIdx; i++){
				vals.add(getCellAsString(rowCells[i]));
			}
			rowVals.add(vals.toArray());
		}
	}

	@Override
	public void finishRead() {
		List<Object> beans = new ArrayList<Object>();
		for(Object[] row : rowVals){
			LinkedHashMap<String, Object> bean = new LinkedHashMap<String, Object>();
			for(int i = 0; i < row.length; i++){
				bean.put(headers[i], row[i] == null ? null : String.valueOf(row[i]));
			}
			beans.add(bean);
		}
		
		out.println(JsonUtil.toJson(beans));
	}
	
}