/**
 * 
 */
package com.study.lang;

import java.util.List;

/**
 * @author mengxianming
 *
 * 2015年8月11日
 */
public class PrintMatrix {

	public void printMatrix(int[][] a){
		for(int i = 0, j = a[i].length - 1; j >= 0; j--){
			printDiagonal(a, i, j);
			System.out.println();
		}

		for(int i = 1, j = 0; i < a.length; i++){
			printDiagonal(a, i, j);
			System.out.println();
		}
	}
	private void printDiagonal(int[][] a, int i, int j){
		System.out.print(a[i][j] + " ");
		if(i + 1 < a.length && j + 1 < a[i + 1].length){
			printDiagonal(a, i + 1, j + 1);
		}		
	}

	public static void main(String[] args){
		int[][] intary=new int[][]{
				{ 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },    
				{ 9, 10, 11, 12 },    
				{13, 14, 15, 16 } };  
		new PrintMatrix().printMatrix(intary);
	}
}

