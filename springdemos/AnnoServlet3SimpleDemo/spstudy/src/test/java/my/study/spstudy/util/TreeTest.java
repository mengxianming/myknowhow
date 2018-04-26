package my.study.spstudy.util;

import org.junit.Test;

public class TreeTest {	
	enum Status{
		INIT, LEFT_VISITED, RIGHT_VISITED
	}
	
	public static interface Visitor{
		void visit(TNode node);
	}
	public static class TNode{
		private TNode parent;
		private  TNode left;
		private TNode right;
		private Object data;
		private Status status = Status.INIT;
		
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
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}		
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	public static class Tree{
		private TNode root;

				
		public void preOrder(Visitor visitor){
			TNode p = root;
			while(p != null){
				
				switch(p.getStatus()){
				case INIT: 
					visitor.visit(p);
					
					if(p.getLeft() != null){
						p.status = Status.LEFT_VISITED;
						p = p.getLeft();
						break;
					}
					
					if (p.getRight() != null){
						p.status = Status.RIGHT_VISITED;
						p = p.getRight();
						break;
					}
					
					//p.status = Status.INIT;
					p = p.getParent();
					break;		
				case LEFT_VISITED:
					if (p.getRight() != null){
						p.status = Status.RIGHT_VISITED;
						p = p.getRight();
						break;
					}
					
					p.status = Status.INIT;
					p = p.getParent();
					break;
				case RIGHT_VISITED:
					p.status = Status.INIT;
					p = p.getParent();
					break;
				}
			}
		}
		
		public void inOrder(Visitor visitor){
			TNode p = root;
			while(p != null){
				
				switch(p.getStatus()){
				case INIT: 
										
					if(p.getLeft() != null){
						p.status = Status.LEFT_VISITED;
						p = p.getLeft();
						break;
					}
					
					visitor.visit(p);
					
					if (p.getRight() != null){
						p.status = Status.RIGHT_VISITED;
						p = p.getRight();
						break;
					}
					
					//p.status = Status.INIT;
					p = p.getParent();
					break;		
				case LEFT_VISITED:
					
					visitor.visit(p);
					
					if (p.getRight() != null){
						p.status = Status.RIGHT_VISITED;
						p = p.getRight();
						break;
					}
					
					p.status = Status.INIT;
					p = p.getParent();
					break;
				case RIGHT_VISITED:
					p.status = Status.INIT;
					p = p.getParent();
					break;
				}
			}
		}
		
		
		public void preOrderRecursive(Visitor visitor){
			preOrderRecursive(this.root, visitor);
		}
		
		private void preOrderRecursive(TNode root, Visitor visitor){
			if(root == null){
				return;
			}
			visitor.visit(root);
			preOrderRecursive(root.getLeft(), visitor);
			preOrderRecursive(root.getRight(), visitor);
		}

		public static Tree createTree(int num){
			Tree result = new Tree();			
			TNode[] nodes = new TNode[num];
			int cur = 0;
			int curRoot = 0;
			// build root
			TNode root = new TNode();
			root.setData(1);
			nodes[cur++] = root;
			result.root = root;
			
			for(int i = 2; i <= num; i+=2){		
				{
					TNode left = new TNode();
					left.setData(i);
					left.setParent(root);
					root.setLeft(left);
					nodes[cur++] = left;
				}
				
				if(i + 1 <= num){
					TNode right = new TNode();
					right.setData(i + 1);
					right.setParent(root);
					root.setRight(right);
					nodes[cur++] = right;
				}
							
				root = nodes[++curRoot];
			}
			
			return result;
		}
	}
	
	@Test
	public void testTree(){
		Tree tree = Tree.createTree(200000);
		
		long ts;
		long t1 = 0, t2 = 0;
		Visitor visitor = new Visitor(){

			@Override
			public void visit(TNode node) {
				for(int i=0; i < 3; i++){
					// 空转耗时
					Math.sin(i);
				}
				
			}
			
		};
		for(int i = 0; i < 300; i++){		
			
			ts  = System.currentTimeMillis();
			tree.preOrderRecursive(visitor);
			t2 += System.currentTimeMillis() - ts;
			
			ts = System.currentTimeMillis();
			tree.preOrder(visitor);
			t1 += System.currentTimeMillis() - ts;			
			
		}
		
		System.out.println("preOrder cost:" + t1);
		System.out.println("preOrderRecursive cost:" + t2);
		
	}
	
	@Test
	public void testTree2(){
		Tree tree = Tree.createTree(8);
		Visitor visitor = new Visitor(){

			@Override
			public void visit(TNode node) {
				System.out.print(node.getData() + " ");
			}
			
		};
		for(int i = 0; i < 3; i++){
			tree.preOrder(visitor);
			System.out.println();
		}
	}
}