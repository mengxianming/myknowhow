/**
 * 
 */
package my.demo.mybatisgendemo;

import org.mybatis.generator.api.ShellRunner;

/**
 * @author admin-2015年11月12日
 *
 */
public class Main {

	/**
	 * @param args
	 * @author admin-2015年11月12日
	 */
	public static void main(String[] args) {
		String configFile = "generatorConfig.xml";
		if(args.length > 0){
			configFile = args[0];
		}
		MyGenPlugin.setUseBase(false);
		String config = Main.class.getClassLoader().getResource(  
				configFile).getFile();  
		String[] arg = { "-configfile", config, "-overwrite" };  
		ShellRunner.main(arg);  

	}

}
