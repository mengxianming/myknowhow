package my.study.exceldemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtil {

	public static InputStream loadFile(String filePath) throws FileNotFoundException {
		InputStream file;
		if(filePath.startsWith("classpath:")){
			String path = filePath.substring(filePath.indexOf(":") + 1);
			file = FileUtil.class.getResourceAsStream(path);			
		}else{
			file = new FileInputStream(filePath);
		}
		return file;
	}

}
