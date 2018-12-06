package my.study.spstudy.util;

import java.util.Arrays;
import java.util.List;

import org.mybatis.caches.memcached.StringUtils;

import com.alibaba.fastjson.JSON;


/**
 * 堆、、、可用于优先队列
 * 
 * @author bricks.mong@2018年4月28日
 * @version   
 * @since JDK 1.7
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Heap<T extends Comparable<T>>{	
	private Object[] nodes = new Object[1]; // node[0]不使用、第一个元素从1开始
	private int size = 0;
	
	public int getSize() {
		return size;
	}
	private int getLength(){
		return nodes.length - 1;
	}
	
	public void add(T data){
		ensureCapacity(getSize() + 1);
		nodes[++size] = data;
		
		siftUp();// 自底向上调整堆
	}
	
	
	public T pop(){	
		if(getSize() == 0){
			return null;
		}
		T result = (T) nodes[1];
		nodes[1] = nodes[size];
		nodes[size--] = null;
		siftDown(1);// 自上向下调整堆
		return result;
	}
	

	private void siftUp() {
		int i = size;
		int p = i / 2;
		Comparable tgt = (Comparable) nodes[i];
		while(p != 0){
			if(tgt.compareTo(nodes[p]) <= 0){// 找到插入的位置
				break;
			}
			
			nodes[i] = nodes[p]; // 父节点下移	
			i = p;  // 指针上移
			p = i / 2;			
		}
		

		nodes[i] = tgt; // 插入目标位置		
	}
	
	private int siftDown(int p) {	
		T tgt =  (T)nodes[p]; 
		while(true){		
			int m = biggerChild(p);
			if(m == 0 || tgt.compareTo((T)nodes[m]) >= 0){
				break;			
			}
			
			nodes[p] = nodes[m];
			p = m;
		}		

		nodes[p] = tgt; // 插入目标位置	
		
		return p;
	}
	
	

	private int biggerChild(int p) {
		if(p * 2 > size){
			return 0;
		}
		T left = (T) nodes[p  * 2]; 
		
		if(p * 2 + 1 > size){
			return p * 2;
		}
		
		T right = (T) nodes[p  * 2 + 1]; 
		
		return left.compareTo(right) >= 0 ? p * 2 : p* 2 + 1;
	}
	
	private void ensureCapacity(int newSize) {
		if(newSize > getLength()){
			nodes = Arrays.copyOf(nodes, Math.max((int)(nodes.length * 1.5), newSize) + 1);			
		}		
	}
	
	public void addAll(List<T> list) {
		if(list != null){
			for(T data : list){
				this.add(data);
			}
		}		
	}
	
	
	@Override
	public String toString() {
		return StringUtils.join(nodes, ",");
	}
	
	public static void main(String[] args) {
		Heap<Integer> heap = new Heap<Integer>();
		heap.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		System.out.println(heap);
		
		while(heap.size > 0){
			System.out.print(heap.pop() + " ");
		}
	}
	
}