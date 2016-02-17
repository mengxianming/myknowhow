package my.study.exceldemo;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.util.IOUtils;

import my.study.exceldemo.vo.Config;

public class Main{
	public static void main(String[] args) throws Exception{
		if(args.length == 0){
			System.out.println("usage cmd {[-f configFilePath] | [configJson]}]");
			return;
		}
		
		String configJson;
		if(args.length > 1 && "-f".equals(args[0])){			
			InputStream stream = new FileInputStream(args[1]);
			byte[] content = IOUtils.toByteArray(stream);
			configJson = new String(content, "utf8");
		}else{
			configJson = args[0];
		}
		Config config = JsonUtil.fromJson(configJson, Config.class);

		ReadXls readXls = new ReadXls();
		CellReader cReader;
		if(config.getMode() == 0){
			cReader = new PrintCellReader();
		}else if(config.getMode() == 1){
			cReader = new SQLGenCellReader(config, System.out);
		}else if(config.getMode() == 2){
			cReader = new EnumGenCellReader(config, System.out);
		}else{
			throw new IllegalArgumentException("配置项有误、只支持mode=[0, 1, 2], 实际mode=" + config.getMode());
		}
		readXls.setCellReader(cReader);
		readXls.readXls(config.getExcelFilePath(), config.getSheetNum() - 1, 
				config.getStartRowNum() - 1, config.getStartColNum() - 1, config.getMaxColCount());
		
	}
}