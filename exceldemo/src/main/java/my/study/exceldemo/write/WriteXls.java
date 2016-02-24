package my.study.exceldemo.write;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 
 
public class WriteXls {
 
   
    private static final int START_ROW = 1;//第二行
	private static final int START_COL = 1;//第二列

	public void writeXls(String filePath, String sheetName, ContentProvider contentProvider) throws Exception {
        
        // 创建Excel文档
        HSSFWorkbook hwb;
        File file = new File(filePath);
        if(file.exists()){
        	POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
    		hwb = new HSSFWorkbook(poifsFileSystem);
        }else{
        	 hwb = new HSSFWorkbook();
        }
        
		
        // sheet 对应一个工作页
        Object[][] contents = contentProvider.getContents();
        HSSFSheet sheet = hwb.getSheet(sheetName);
        if(sheet == null){
        	sheet = hwb.createSheet(sheetName);
        }        
        
        for (int i = 0; i < contents.length; i++) {
        	Object[] data = contents[i];
        	 HSSFRow row = sheet.createRow(START_ROW + i); 
             for(int j = 0; j < data.length; j++){
            	 HSSFCell cell = row.createCell(START_COL + j);
            	 writeCell(cell, data[j]);
             }
        }
        
        // 创建文件输出流，准备输出电子表格
        file.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(filePath);
        hwb.write(out);
        out.close();
        hwb.close();
    }

	private void writeCell(HSSFCell cell, Object val) {
		try {
			if(val != null){
				Method method;
				try {
					method = cell.getClass().getMethod("setCellValue", val.getClass());
				} catch (NoSuchMethodException  e) {
					method = null;
				}
				if(method != null){
					method.invoke(cell, val);
				}else{
					if(val instanceof Number){						
						cell.setCellValue(((Number)val).doubleValue());
					}else{
						cell.setCellValue(String.valueOf(val));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		
	}
 
}