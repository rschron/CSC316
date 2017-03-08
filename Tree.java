package tree;

import java.util.Scanner;

public class Tree<T> {
	String[] preorder;
	String[] postorder;
	
	public Node buildTree(int size, int prestart, int poststart){
		Node<T> root;
		
		return root;
	}
	
	public void findNode(Node<T> node){
		
	}
	
	
	public void traverse(){
		
	}
	
	public static void main(String args[]){
		Tree<String> tree = new Tree<String>();
		Scanner sc = new Scanner(System.in);
		int orderSize;
		
		String pre = sc.nextLine();
		String post = sc.nextLine();
		String delims = "[ ,]+";
		
		tree.preorder = pre.split(delims);
		tree.postorder = post.split(delims);
		orderSize = tree.preorder.length;
		
		Node root = tree.buildTree(orderSize, 0, 0);
		
		sc.close();
		
	}
	
}
