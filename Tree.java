package tree;

import java.util.Scanner;

public class Tree<T> {
	String[] pretrav;
	String[] posttrav;
	Node<T> root = null;
	
	
	
	@SuppressWarnings("unchecked")
	public Node<T> buildTree(int size, int prestart, int poststart){
		
		if(size <= 0){
			return null;
		}
		
		if(pretrav[prestart].equals(posttrav[size-1])){
			Node<T> child = (Node<T>) new Node<String>(pretrav[prestart]);
			size--;
			prestart++;
			child.setChild(buildTree(size, prestart, poststart));
		} else if(size == 0){
			return null;	
		}else{
			//find size
			int i;
			for(i = 0; i < size; i++){
				if(pretrav[prestart].equals(posttrav[i])){
					
					root.setChild(buildTree(i+1, prestart, poststart));
					prestart = i+1;
				}
			}
		}
		
		
		return root;
	}
	
	public void findNode(Node<T> node){
		
	}
	
	
	public void traverse(Node<T> base){
		
		base = (Node<T>) root;
		
		for(Node<T> child: base.children){
			if(child == null){
				System.out.println(root.data);
			} else{
				traverse(child);
			}
		}
	}
	
	public static void main(String args[]){
		
		Tree<String> tree = new Tree<>();
		
		/*
		Scanner sc = new Scanner(System.in);
		int orderSize;
		*/
		String pre = " D, H, B, G, M, W, F, T, X, Z, C, R, P, Q, N.";
		String post =  "G, M, W, F, B, X, Z, T, R, P, C, H, N, Q, D.";
		
		String delims = "[ ,]+";
		
		tree.pretrav = pre.split(delims);
		tree.posttrav = post.split(delims);
		int orderSize = tree.pretrav.length;
		
		tree.root = tree.buildTree(orderSize, 0, 0);
		tree.traverse(tree.root);
		
		//sc.close();
		
	}
	
}
