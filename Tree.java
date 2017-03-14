package tree;

import java.util.Scanner;

public class Tree<T> {
	String[] pretrav =  {"D", "H", "B", "G", "M", "W", "F", "T", "X", "Z", "C", "R", "P", "Q", "N"};
	String[] posttrav = {"G", "M", "W", "F", "B", "X", "Z", "T", "R", "P", "C", "H", "N", "Q", "D"};
	Node<T> root = null;
	
	
	
	@SuppressWarnings("unchecked")
	public Node<T> buildTree(int size, int prestart, int poststart){
		
		if(size <= 0){
			return null;
		}
		
		Node<T> child = (Node<T>) new Node<String>(pretrav[prestart]);
		prestart++;
		size--;
		
		
		int i;
		for(int j = prestart; j <= size; j+= i){
			for(i = poststart; i < size; i++){
				if(pretrav[j].equals(posttrav[i])){
					break;
				}
			}
			child.setChild(buildTree(i+1, j, i));
			poststart = i + 1;
		}
		
		
		return child;
	}
	
	public void findNode(Node<T> node){
		
	}
	
	
	public void traverse(Node<T> base){
		
		for(Node<T> child: base.children){
			traverse(child);
		}
		System.out.println(base.data);
	}
	
	public static void main(String args[]){
		
		Tree<String> tree = new Tree<>();
		
		/*
		Scanner sc = new Scanner(System.in);
		int orderSize;
		*/
		
		/*
		String pre = " D, H, B, G, M, W, F, T, X, Z, C, R, P, Q, N.";
		String post =  "G, M, W, F, B, X, Z, T, R, P, C, H, N, Q, D.";
		
		String delims = "[ ,]+";
		
		tree.pretrav = pre.split(delims);
		tree.posttrav = post.split(delims);
		*/
		
		int orderSize = tree.pretrav.length;
		
		tree.root = tree.buildTree(orderSize, 0, 0);
		tree.traverse(tree.root);
		
		//sc.close();
		
	}
	
}
