package tree;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
	String[] pretrav =  {"D", "H", "B", "G", "M", "W", "F", "T", "X", "Z", "C", "R", "P", "Q", "N"};
	String[] posttrav = {"G", "M", "W", "F", "B", "X", "Z", "T", "R", "P", "C", "H", "N", "Q", "D"};
	public Node<T> root = null;
	
	
	
	@SuppressWarnings("unchecked")
	public Node<T> buildTree(int size, int prestart, int poststart){
		
		
		
		Node<T> child = (Node<T>) new Node<String>(pretrav[prestart]);
		
		if(size <= 1){
			return child;
		}
		
		prestart++;
		size--;
		
		int newSize;
		int i;
		for(int j = 0; j <= size; j+= newSize){
			for(i = 0; i < size; i++){
				if(pretrav[j + prestart].equals(posttrav[i + poststart])){
					break;
				}
			}
			newSize = i+1;
			
			Node<T> temp = buildTree(newSize, prestart + j, poststart + i);
			child.setChild(temp);
			temp.setParent(child);
			poststart += newSize;
			prestart += newSize;
		}
		
		
		return child;
	}
	
	public Node<T> findNode(Node<T> start, T data){
		Node<T> child = start;
		
		if(!child.data.equals(data)){
			for(Node<T> children: start.children){
				 child = findNode(children, data);
				 if(child.data.equals(data)){
					 break;
				 }
			}
		}
		
		return child;
	}
	
	
	public void preOrder(Node<T> base){
		System.out.printf("%s ", base.data);
		for(Node<T> child: base.children){
			preOrder(child);
		}
	}
	
	public void postOrder(Node<T> base){
		for(Node<T> child: base.children){
			postOrder(child);
		}
		System.out.printf("%s ", base.data);
	}
	
	public void levelOrder(Node<T> base){
		
		Node<T> q;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(base);
		
		while(!queue.isEmpty()){
			q =  queue.remove();
			System.out.printf("%s ", q.data);
			
			for(Node<T> child: q.children){
				queue.add(child);
			}
		}
		
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
		System.out.println("Preorder:");
		tree.preOrder(tree.root);
		System.out.println();
		System.out.println("Postorder:");
		tree.postOrder(tree.root);
		System.out.println();
		System.out.println("Level Order:");
		tree.levelOrder(tree.root);
		
		//sc.close();
		
	}
	
}