/**
 * 
 */
package my.demo.mybatisgendemo;

/**
 * @author admin-2015年11月12日
 *
 */
public class MainMogoDb {

	/**
	 * @param args
	 * @author admin-2015年11月12日
	 */
	public static void main(String[] args) {
		MyGenPlugin.setUseBase(false);
		Main.main(new String[]{"generatorConfig-mogodb.xml"});

	}

}
