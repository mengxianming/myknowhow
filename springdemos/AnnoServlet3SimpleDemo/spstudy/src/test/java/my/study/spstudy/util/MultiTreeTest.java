package my.study.spstudy.util;

import org.junit.Test;

import my.study.spstudy.util.MultiTree.TNode;


public class MultiTreeTest {	
	
	@Test
	public void testTree(){
		MultiTree tree = MultiTree.createTree(500000, 4);
		
		long ts;
		long t1 = 0, t2 = 0;
		MultiTree.Visitor visitor = new MultiTree.Visitor(){

			@Override
			public void visit(TNode node) {
				for(int i=0; i < 3; i++){
					// 空转耗时
					Math.sin(i);
					//UUID.randomUUID();
				}
				
			}
			
		};
		for(int i = 0; i < 100; i++){		
			
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
		MultiTree tree = MultiTree.createTree(32, 3);
		MultiTree.Visitor visitor = new MultiTree.Visitor(){

			@Override
			public void visit(TNode node) {
				System.out.print(node.getData() + " ");
			}
			
		};
		
		for(int i = 0; i < 1; i++){
			tree.preOrderRecursive(visitor);
			System.out.println();
			
			tree.preOrder(visitor);
			System.out.println();
						
			tree.postOrder(visitor);
			System.out.println("\n");
		}
	}
}