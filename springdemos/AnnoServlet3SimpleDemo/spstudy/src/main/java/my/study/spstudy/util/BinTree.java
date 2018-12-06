package my.study.spstudy.util;

/**
 * 二叉树
 * 
 * @author bricks.mong@2018年4月26日
 * @version   
 * @since JDK 1.7
 */
public class BinTree{
	enum Status{
		INIT, LEFT_VISITED, RIGHT_VISITED
	}
	enum TraverseOrder{PRE_ORDER, IN_ORDER, POST_ORDER};
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
	
	
	private TNode root;

			
	public void preOrder(Visitor visitor){
		traverseNonR(visitor, TraverseOrder.PRE_ORDER);
	}		

	public void inOrder(Visitor visitor){
		traverseNonR(visitor, TraverseOrder.IN_ORDER);
	}
	
	public void postOrder(Visitor visitor){
		traverseNonR(visitor, TraverseOrder.POST_ORDER);
	}
	
	private void traverseNonR(Visitor visitor, TraverseOrder order ){
		TNode p = root;
		while(p != null){
			
			switch(p.getStatus()){
			case INIT: 
				if(order == TraverseOrder.PRE_ORDER){
					visitor.visit(p);
				}
				
				p.status = Status.LEFT_VISITED;
				if(p.getLeft() != null){
					p = p.getLeft();
				}										
				break;		
			case LEFT_VISITED:
				if(order == TraverseOrder.IN_ORDER){
					visitor.visit(p);
				}
				
				p.status = Status.RIGHT_VISITED;
				if (p.getRight() != null){
					p = p.getRight();
				}
				break;
			case RIGHT_VISITED:
				if(order == TraverseOrder.POST_ORDER){
					visitor.visit(p);
				}
				
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

	public static BinTree createTree(int num){
		BinTree result = new BinTree();			
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
