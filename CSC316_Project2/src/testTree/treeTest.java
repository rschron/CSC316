package testTree;
import org.junit.Test;

import tree.Node;
import tree.proj2;

import static org.junit.Assert.*;

import org.junit.Before;

public class treeTest {

	proj2<String> tree;
	
	@Before
	public void init(){
		tree = new proj2<String>();
		tree.root = new Node<String>("D");
		tree.root.setChild(new Node<String>("H"));
		tree.root.setChild(new Node<String>("Q"));
		
		tree.findNode(tree.root, "H").setChild(new Node<String>("B"));
		tree.findNode(tree.root, "H").setChild(new Node<String>("T"));
		tree.findNode(tree.root, "H").setChild(new Node<String>("C"));
		
		tree.findNode(tree.root, "Q").setChild(new Node<String>("N"));
		
		tree.findNode(tree.root, "B").setChild(new Node<String>("G"));
		tree.findNode(tree.root, "B").setChild(new Node<String>("M"));
		tree.findNode(tree.root, "B").setChild(new Node<String>("W"));
		tree.findNode(tree.root, "B").setChild(new Node<String>("F"));
		
		tree.findNode(tree.root, "T").setChild(new Node<String>("X"));
		tree.findNode(tree.root, "T").setChild(new Node<String>("Z"));
		
		tree.findNode(tree.root, "C").setChild(new Node<String>("R"));
		tree.findNode(tree.root, "C").setChild(new Node<String>("P"));
		
	}
	
	@Test
	public void testBuildTree(){
		System.out.println("Preorder:");
		tree.preOrder(tree.root);
		
		System.out.println("Postorder:");
		tree.postOrder(tree.root);
	}
	
	@Test
	public void testLevelOrder(){
		System.out.println("Level Order:");
		tree.levelOrder(tree.root);
	}
	
}
