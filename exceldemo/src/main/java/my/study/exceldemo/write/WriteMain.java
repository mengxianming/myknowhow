package my.study.exceldemo.write;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.util.IOUtils;

import my.study.exceldemo.vo.Config;
import my.study.jsontool.JsonUtil;

public class WriteMain{
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

		WriteXls writeXls = new WriteXls();
		ContentProvider cp;
		if(config.getWriteOpt().getMode() == 0){
			cp = new TabDelimStringContentProvider(getInput(System.in), null);
		}else if(config.getWriteOpt().getMode() == 1){
			cp = new JsonContentProvider(getInput(System.in), null);
		}else if(config.getWriteOpt().getMode() == 2){
			cp = new DBContentProvider(config, null);
		}else{
			throw new IllegalArgumentException("配置项有误、只支持mode=[0, 1, 2], 实际mode=" + config.getMode());
		}
		
		writeXls.writeXls(config.getExcelFilePath(), config.getWriteOpt().getSheetName(), cp);		
	}

	private static String getInput(InputStream is) throws IOException {
		byte[] content = IOUtils.toByteArray(is);
		return new String(content, "utf8");
	}
}