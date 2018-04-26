package my.study.sprintbootabc;

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.vip.finance.member.gateway.utils.RestSign;

public class MiscTest{
	private static char[] base = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	@Test
	public void test(){
		for(int radix = 2; radix <= 36; radix++){
			System.out.println(toRadix(12345, radix));
		}
	}

	private String toRadix(long n,  int radix) {		
		StringBuilder result = new StringBuilder();
		if(n < radix){
			result.append(base[(int) n]);
		}
		while(n != 0){
			int r = (int) (n % radix);
			n = n / radix;
			result.append(base[r]);			
		}
		
		return result.reverse().toString();
	}
	
	@Test
	public void test2(){
		
		Object repl = "and bus_system in (?)".replace("?", StringUtils.join(platformMapToBusSystem(new Short("3")), ","));
		System.out.println(repl);
	}
	
	private List<Integer> platformMapToBusSystem(Short platform) {
		// bus_system: 所属子系统（1、pc推广，2、WAP推广3、唯享客4、移动APP推广（无线企业版），5、无线推广）
		// platform: 平台（1:PC,2:移动,3:唯享客）
		Integer[][] map =  new Integer[][]{
			{},
			{1, 2},
			{4, 5},
			{3}
		};
		return Arrays.asList(map[platform]);
	}
}