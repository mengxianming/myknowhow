package my.study.spstudy;

import org.junit.Test;

public class MiscTest{
	
	@Test
	public void testDoubleToString(){
		for(int i =0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				String sd = "1." + i + j;
				double d = Double.parseDouble(sd);
				System.out.print(d + ", ");
			}
			System.out.println();
		}
	}
	
	
}

