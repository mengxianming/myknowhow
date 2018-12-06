package my.study.spstudy.util;

import org.junit.Test;

import my.study.spstudy.util.BinTree.TNode;

public class BinTreeTest {	
	
	@Test
	public void testTree(){
		BinTree tree = BinTree.createTree(5000000);
		
		long ts;
		long t1 = 0, t2 = 0;
		BinTree.Visitor visitor = new BinTree.Visitor(){

			@Override
			public void visit(TNode node) {
				for(int i=0; i < 3; i++){
					// 空转耗时
					Math.sin(i);
					//UUID.randomUUID();
				}
				
			}
			
		};
		for(int i = 0; i < 50; i++){		
			
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
		BinTree tree = BinTree.createTree(8);
		BinTree.Visitor visitor = new BinTree.Visitor(){

			@Override
			public void visit(TNode node) {
				System.out.print(node.getData() + " ");
			}
			
		};
		
		for(int i = 0; i < 2; i++){
			tree.preOrder(visitor);
			System.out.println();
			
			tree.inOrder(visitor);
			System.out.println();
			
			tree.postOrder(visitor);
			System.out.println("\n");
		}
	}
}