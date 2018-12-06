package my.study.spstudy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树
 * 
 * @author bricks.mong@2018年4月26日
 * @version   
 * @since JDK 1.7
 */
public class MultiTree{
	enum TraverseOrder{PRE_ORDER,  POST_ORDER};
	
	public static interface Visitor{
		void visit(TNode node);
	}
	
	public static class TNode{
		private TNode parent;
		private List<TNode> children = new ArrayList<>();
		private Object data;
		private int  visitStatus;
		
		public TNode getParent() {
			return parent;
		}
		public void setParent(TNode parent) {
			this.parent = parent;
		}
		public List<TNode> getChildren() {
			return children;
		}
		public void setChildren(List<TNode> children) {
			this.children = children;
		}
		
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public int getVisitStatus() {
			return visitStatus;
		}
		public void setVisitStatus(int visitStatus) {
			this.visitStatus = visitStatus;
		}
		
		public int getChildrenSize(){
			return this.children.size();
		}
		public TNode getChild(int i){
			return i < this.children.size() ? this.children.get(i) : null;
		}
		
		public void addChild(TNode child){
			children.add(child);
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
	
	public void postOrder(Visitor visitor){
		traverseNonR(visitor, TraverseOrder.POST_ORDER);
	}
	
	private void traverseNonR(Visitor visitor, TraverseOrder order ){
		TNode p = root;
		while(p != null){
			
			if(order == TraverseOrder.PRE_ORDER 
					&& p.visitStatus == 0){
				visitor.visit(p);
			}
			
			if(p.visitStatus < p.children.size()){
				p = p.children.get(p.visitStatus++);
				continue;
			}
			
			if(order == TraverseOrder.POST_ORDER){
				visitor.visit(p);
			}
			p.visitStatus = 0;
			p = p.getParent();					
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
		if(root.getChildren() != null){
			for(TNode node : root.getChildren()){
				preOrderRecursive(node, visitor);
			}
		}
		
	}

	public static MultiTree createTree(int num, int childrenSize){
		MultiTree result = new MultiTree();			
		TNode[] nodes = new TNode[num];
		int cur = 0;
		int curRoot = 0;
		// build root
		TNode root = new TNode();
		root.setData(1);
		nodes[cur++] = root;
		result.root = root;
		
		
		for(int i = 2; i <= num; i+=childrenSize){		
			for(int j = 0; j < childrenSize && i + j <= num; j++){				
				TNode child = new TNode();
				child.setData(i + j);
				child.setParent(root);
				root.addChild(child);
				nodes[cur++] = child;
			}
			
									
			root = nodes[++curRoot];
		}
		
		return result;
	}
}
