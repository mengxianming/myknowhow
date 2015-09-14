package com.study.lang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class BasicAlgStudy{	
	public void printLinkedListResversed(List<?> list){
		Iterator<?> it = list.iterator();
		printIt(it);
	}

	/**
	 * @param it
	 */
	private void printIt(Iterator<?> it) {		
		if(it.hasNext()){
			Object e = it.next();
			printIt(it);
			System.out.println(e);
		}
		

	}
	
	@Test
	public void testPrintLinkedListResversed(){
		List<?> list = Arrays.asList(1, 2, 3, 4);
		printLinkedListResversed(list);
	}
}
