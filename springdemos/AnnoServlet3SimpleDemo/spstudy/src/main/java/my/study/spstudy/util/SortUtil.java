package my.study.spstudy.util;

/**
 * 排序工具类
 * 各种排序算法的实现
 * @author bricks.mong@2018年5月3日
 * @version   
 * @since JDK 1.7
 */
public class SortUtil{
	
	/**
	 * 获取最大值与最小值
	 *   复杂度O(3n/2)
	 * @author bricks.mong@2018年5月3日  
	 * @param data
	 * @return
	 */
	public static <T extends Comparable<T>> T[] minMax(T[] data){
		assert(data.length > 0);
		
		T min, max;
		int i=0;
		if(isOdd(data.length)){
			min = data[0];
			max = data[0];
			i++;
		}else{
			if(data[0].compareTo(data[1]) <= 0){
				min = data[0];
				max = data[1];
			}else{
				min = data[1];
				max = data[0];
			}
			i +=2;
		}
		
		// 剩余元素个数为偶数、故不需要边界检查
		for(; i < data.length; i +=2){
			if(data[i].compareTo(data[i + 1]) <= 0){
				if(data[i].compareTo(min) < 0){
					min = data[i];
				}
				
				if(data[i + 1].compareTo(max) > 0){
					max = data[i + 1];
				}
			}else{
				if(data[i].compareTo(max) > 0){
					max = data[i];
				}
				
				if(data[i + 1].compareTo(min) < 0){
					min = data[i + 1];
				}
			}
		}
		
		return new T[]{min, max};
	}
//	public void countingSort(int[] data, )

	public static boolean isOdd(int n) {
		return (n & 1) == 1;
	}
	
	public static void main(String[] args) {
		System.out.println(minMax(Arrays));
	}
}