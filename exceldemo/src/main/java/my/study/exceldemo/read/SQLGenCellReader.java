package my.study.exceldemo.read;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import my.study.exceldemo.vo.Config;

public class SQLGenCellReader extends AbstractCellReader {
	private Config config;
	private PrintStream out;
	private String sqlTpl;
	private int maxColIdx;

	public SQLGenCellReader() {

	}
	public SQLGenCellReader(Config config, PrintStream out) {
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
			StringBuilder sb = new StringBuilder("insert into " + config.getSqlGenOpt().getTableName() + " (");
			for(int i = 0; i < rowCells.length; i++){
				sb.append(getCellAsString(rowCells[i]));
				sb.append(i == maxColIdx ? ")" : ", ");
			}			
			sb.append("values(");
			for(int i = 0; i < rowCells.length; i++){
				sb.append("'%s'");
				sb.append(i == maxColIdx ? ");" : ", ");
			}
			this.sqlTpl = sb.toString();
			
		}else{
			if(rowCells.length < maxColIdx + 1){
				return;
			}
			List<Object> vals = new ArrayList<Object>();
			for(int i = 0; i < rowCells.length && i <= maxColIdx; i++){
				vals.add(getCellAsString(rowCells[i]));
			}
			String sql = String.format(sqlTpl, vals.toArray());
			out.println(sql);
		}
	}
	

	
}