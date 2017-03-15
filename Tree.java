package tree;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
	String[] pretrav = new String[100]; //= {"D", "H", "B", "G", "M", "W", "F", "T", "X", "Z", "C", "R", "P", "Q", "N"};
	String[] posttrav = new String [100]; //= {"G", "M", "W", "F", "B", "X", "Z", "T", "R", "P", "C", "H", "N", "Q", "D"};
	
	public Node<T> root = null;
	
	
	
	@SuppressWarnings("unchecked")
	public Node<T> buildTree(int size, int prestart, int poststart){
		
		Node<T> child = (Node<T>) new Node<String>(pretrav[prestart]);
		
		if(size == 1){
			return child;
		}
		
		prestart++;
		size--;
		
		int newSize;
		int i;
		for(int j = prestart; j <= (size + prestart - 1); j+= newSize){
			for(i = poststart; i < (size + poststart); i++){
				if(pretrav[j].equals(posttrav[i])){
					break;
				}
			}
			newSize = i - poststart + 1;
			
			Node<T> temp = buildTree(newSize,j,poststart);
			poststart += newSize;
			child.setChild(temp);
			temp.setParent(child);
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
		
		Scanner sc = new Scanner(System.in);
		int orderSize;
		
		
		String pre = sc.nextLine();
		String post = sc.nextLine();
		
		String delims = "[ <>,.]+";
		
		tree.pretrav = pre.split(delims);
		tree.posttrav = post.split(delims);
		
		System.out.println("Preorder:");
		for(int i = 0; i < tree.pretrav.length; i++){
			System.out.printf("%s ", tree.pretrav[i]);
		}
		
		System.out.println();
		System.out.println("Postorder:");
		for(int i = 0; i < tree.posttrav.length; i++){
			System.out.printf("%s ", tree.posttrav[i]);
		}
		
		
		orderSize = tree.pretrav.length;
		
		
		tree.root = tree.buildTree(orderSize, 0, 0);
		System.out.println("Preorder:");
		tree.preOrder(tree.root);
		System.out.println();
		System.out.println("Postorder:");
		tree.postOrder(tree.root);
		System.out.println();
		System.out.println("Level Order:");
		tree.levelOrder(tree.root);
		
		sc.close();
		
	}
	
}