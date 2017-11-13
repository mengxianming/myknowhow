package my.study.sprintbootabc.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class BSTree{
	private TNode root;
		
	public static class TNode{
		private Integer key;
		private Object data;
		
		private TNode parent;
		private TNode left;
		private TNode right;
		
		public TNode(Integer key){
			this.key = key;
		}
		public Integer getKey() {
			return key;
		}
		public void setKey(Integer key) {
			this.key = key;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public TNode getParent() {
			return parent;
		}
		public void setParent(TNode parent) {
			this.parent = parent;
		}
		public TNode getLeft() {
			return left;
		}
		public void setLeft(TNode left) {
			this.left = left;
		}
		public TNode getRight() {
			return right;
		}
		public void setRight(TNode right) {
			this.right = right;
		}		
		
	}

	public class StopTraverseException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		
		private TNode previous;
		private TNode current;
		
		public StopTraverseException(TNode previous, TNode current) {
			super();
			this.previous = previous;
			this.current = current;
		}
		
		public TNode getPrevious() {
			return previous;
		}
		public TNode getCurrent() {
			return current;
		}
		
	}

	interface Visitor{
		void visit(TNode node) throws StopTraverseException;
	}
	
	enum VisitMode{
		PRE_ORDER, IN_ORDER, POST_ORDER;
	}

	private class StackEle{
		TNode tnode;
		TNode nextChild;
		
		public StackEle set(TNode tnode){
			this.tnode = tnode;
			this.nextChild = tnode.getLeft() != null ? tnode.getLeft() : tnode.getRight();
			return this;
		}
		
		public TNode getTnode() {
			return tnode;
		}
		public void increaseBacktraceCount(){
			this.nextChild = tnode.getLeft() == nextChild ? tnode.getRight() : null;
		}
		
		public TNode getNextChild(){
			return nextChild;
		}			
		
	}
	
	private class StackElePool{		
		Deque<StackEle> pool = new ArrayDeque<>(50);
				
		public StackEle get(TNode tnode){
			
			if(pool.isEmpty()){
				StackEle ele = new StackEle().set(tnode);
				return ele;
			}else{				
				return pool.pop().set(tnode);
			}
			
		}
		
		public void returnBack(StackEle ele){
			pool.push(ele);
		}
	}
	
	private class FindResult{
		TNode previous;
		TNode hitNode;
		
		public FindResult(TNode previous, TNode hitNode) {
			super();
			this.previous = previous;
			this.hitNode = hitNode;
		}
		
		public boolean hit(){
			return hitNode != null;
		}
		
	}
	
	
	public static BSTree createTree(int size){
		int[] keys = new int[size];
		
		Random ran = new Random();
		for(int i = 0; i < size; i++){
			keys[i] = ran.nextInt(size * 100);
		}
		
		return createTree(keys);
	}
	
	public static BSTree createTree(int[] keys){
		BSTree result = new BSTree();
		
		
		for(int key : keys){
			
			// find the key
			FindResult findResult = result.find(key);
			if(findResult.hit()){
				continue;
			}
			
			TNode addNode = new TNode(key);
			if(findResult.previous == null){
				result.root = addNode;
				continue;
			}
			
			if(key < findResult.previous.getKey()){
				findResult.previous.setLeft(addNode);				
			}else{
				findResult.previous.setRight(addNode);
			}
			addNode.setParent(findResult.previous);
		}
		
		return result;
	}
	
	private FindResult find(Integer key){
		TNode pre = null;
		TNode cur = root;
		while(cur != null){
			int cr = key.compareTo(cur.getKey());
			if(cr == 0){
				return new FindResult(pre, cur);
			}
			if(cr < 0){
				pre =  cur;
				cur = cur.getLeft();
			}else{
				pre =  cur;
				cur = cur.getRight();
			}
		}
		
		return  new FindResult(pre, cur);
	}
		
	
	public void traverseRecursive(VisitMode visitMode, Visitor visitor) throws StopTraverseException{
		if(visitMode == VisitMode.PRE_ORDER){
			traversePreOrderRecursive(root, visitor);		
		}else if(visitMode == VisitMode.IN_ORDER){
			traverseInOrderRecursive(root, visitor);		
		}else{
			traversePostOrderRecursive(root, visitor);	
		}
	}
	
	public void traverseLevel(Visitor visitor) throws StopTraverseException{
		if(root == null){
			return;
		}
		Queue<TNode> queue =  new ArrayDeque<TNode>();		
		visitor.visit(root);
		queue.add(root);
		while(!queue.isEmpty()){
			TNode top = queue.poll();
			if(top.getLeft() != null){
				visitor.visit(top.getLeft());
				queue.add(top.getLeft());				
			}
			if(top.getRight() != null){
				visitor.visit(top.getRight());
				queue.add(top.getRight());
			}			
		}
	}
	
	public void traversePreOrderNoneRecursive(Visitor visitor) throws StopTraverseException{
		if(root == null){
			return;
		}
		
		StackElePool pool = new StackElePool();
		Deque<StackEle> stack =  new ArrayDeque<StackEle>(200);
		visitor.visit(root);
		stack.push(pool.get(root));
		while(!stack.isEmpty()){
			StackEle top = stack.peek();
			TNode child = top.getNextChild();
			if(child == null){
				pool.returnBack(stack.pop());
				if(stack.peek() != null){
					stack.peek().increaseBacktraceCount();
				}
			}else{
				visitor.visit(child);
				stack.push(pool.get(child));
			}
		}
		
	}
	
	private void traversePreOrderRecursive(TNode curNode, Visitor visitor) throws StopTraverseException{
		if(curNode == null){
			return;
		}
		visitor.visit(curNode);
		traversePreOrderRecursive(curNode.getLeft(), visitor);
		traversePreOrderRecursive(curNode.getRight(), visitor);
		
	}
	
	private void traverseInOrderRecursive(TNode curNode, Visitor visitor) throws StopTraverseException{
		if(curNode == null){
			return;
		}		
		traverseInOrderRecursive(curNode.getLeft(), visitor);
		visitor.visit(curNode);
		traverseInOrderRecursive(curNode.getRight(), visitor);		
	}
	
	private void traversePostOrderRecursive(TNode curNode, Visitor visitor) throws StopTraverseException{
		if(curNode == null){
			return;
		}		
		traversePostOrderRecursive(curNode.getLeft(), visitor);		
		traversePostOrderRecursive(curNode.getRight(), visitor);
		visitor.visit(curNode);
	}
	
	public void print(){
		traverseLevel(new Visitor(){
			TNode pre = null;

			@Override
			public void visit(TNode node) throws StopTraverseException {
				if(level(pre) != level(node)){
					System.out.println();
				}
				System.out.print(String.format("%s(%s, %s)  ", node.getKey(),
						node.getLeft() == null ? "n" : node.getLeft().getKey(),
								node.getRight() == null ? "n" : node.getRight().getKey()));	
				
				pre = node;
			}

			private Object level(TNode node) {
				int lvl = 0;
				while(node != null){
					lvl++;
					node = node.getParent();
				}
				return lvl;
			}
			
		});
		System.out.println();
	}
	
	public static void main(String[] args) throws StopTraverseException{
//		basicTest();
		testTime();
	}
	
	
	public static void basicTest() throws StopTraverseException{
		//BSTree tree = BSTree.createTree(new int[]{439,266, 906,226,66,48, 134, 0, 64, 67, 135, 134});
		BSTree tree = BSTree.createTree(new int[]{439,266, 134, 0, 64, 67, 135, 134});
//		BSTree tree = BSTree.createTree(50);
		tree.print();
		
		Visitor v = new Visitor(){

			@Override
			public void visit(TNode node) throws StopTraverseException {
				System.out.print(node.getKey() + " ");
				
			}
			
		};
		tree.traversePreOrderNoneRecursive(v);
		System.out.println();
		tree.traverseRecursive(VisitMode.PRE_ORDER, v);
	}
	
	public static void testTime() throws StopTraverseException{
		int nodesCount = 10000;
		BSTree tree = BSTree.createTree(nodesCount);
//		BSTree tree = BSTree.createTree(new int[]{322, 74, 691, 19, 129, 482, 908, 43, 249, 149});
		tree.print();
		
		final AtomicLong counter = new AtomicLong(0);
		Visitor v = new Visitor(){

			@Override
			public void visit(TNode node) throws StopTraverseException {
//				System.out.print(node.getKey() + " ");
				counter.getAndAdd(node.getKey());
				
			}
			
		};
		
		
		int times =  10000;
		long ts = System.currentTimeMillis();
		for(int i = 0; i < times; i++){
			tree.traverseRecursive(VisitMode.PRE_ORDER, v);
		}		
		System.out.println(String.format("scale(%s nodes, %s times), result=%s, cost time recursive= %s",  nodesCount, times, counter, (System.currentTimeMillis() - ts)));
		
		counter.set(0);
		ts = System.currentTimeMillis();
		for(int i = 0; i < times; i++){
			tree.traversePreOrderNoneRecursive(v);
		}
		System.out.println(String.format("scale(%s nodes, %s times), result=%s, cost time noneRecursivePreOrder= %s",  nodesCount, times, counter, (System.currentTimeMillis() - ts)));
		
		
		counter.set(0);
		ts = System.currentTimeMillis();
		for(int i = 0; i < times; i++){
			tree.traverseLevel(v);
		}
		System.out.println(String.format("scale(%s nodes, %s times), result=%s, cost time noneRecursiveLevel= %s",  nodesCount, times, counter, (System.currentTimeMillis() - ts)));
		
	}
}

