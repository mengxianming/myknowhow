package my.study.exceldemo;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import my.study.exceldemo.vo.Config;

public class EnumGenCellReader extends AbstractCellReader {
	
	private PrintStream out;
	List<Object[]> items = new ArrayList<Object[]>();
	private Config config;

	public EnumGenCellReader() {

	}
	
	public EnumGenCellReader(Config config, PrintStream out) {
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
		Object[] item = new Object[3];
		
		for(int i = 0; i < rowCells.length; i++){
			Cell cell = rowCells[i];
			if(cell.getColumnIndex() + 1 == config.getStartColNum() + config.getEnumGenOpt().getEnumItemNameIdx()){
				item[0] = getCellAsString(cell);
			}
			if(cell.getColumnIndex() + 1 == config.getStartColNum() + config.getEnumGenOpt().getKeyIdx()){
				item[1] = getCellAsString(cell);
			}
			if(cell.getColumnIndex() + 1 == config.getStartColNum() + config.getEnumGenOpt().getValIdx()){
				item[2] = getCellAsString(cell);
			}			
		}		
		items.add(item);
	}
	
	@Override
	public void finishRead() {
		VelocityEngine ve = new VelocityEngine();
		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 
		 ve.init();
		 
		 Template t = ve.getTemplate("enumtpl.vm");
		 VelocityContext ctx = new VelocityContext();
		 
		 ctx.put("pkgName", config.getEnumGenOpt().getPkgName());
		 ctx.put("className", config.getEnumGenOpt().getClassName());
		 ctx.put("items", items);
		 		 
		 StringWriter sw = new StringWriter();		 
		 t.merge(ctx, sw);
		 
		 out.println(sw.toString());
	}

	
}