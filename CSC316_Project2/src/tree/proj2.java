package tree;

import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Ryan Schron
 *
 * The Tree<T> class represents a non-binary tree structure consisting of Node<T> objects.
 * The tree is designed to read a preorder and post order traversal from stdin and construct the
 * appropriate tree using the buildTree() method.  It it will then accept queries from stdin regarding
 * the relationship of two nodes.  Finally, the program will print the tree using a level order traversal.
 * 
 * @param <T>
 */
public class proj2<T> {
	/** Parsed preorder traversal**/
	String[] pretrav;
	/** Parsed post order traversal**/
	String[] posttrav;
	
	/**Root of the tree**/
	public Node<T> root = null;
	
	
	/**
	 * Recursively builds the tree with the given preorder and postorder traversals, and returns
	 * the root of the tree.
	 * @param size number of nodes in the tree.
	 * @param prestart location in pretrav of the root.
	 * @param poststart location in posttrav of the first child of the current root.
	 * @return Node<T> root
	 */
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
	
	/**
	 * Finds a given node in the tree based on that node's data.
	 * @param start root of the tree (or sub tree).
	 * @param data data to find in the tree.
	 * @return Node with the data of interest.
	 */
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
	
	
	/**
	 * Prints the preorder traversal of the tree.  Used to test
	 * the buildTree method.
	 * @param base root of the tree.
	 */
	public void preOrder(Node<T> base){
		System.out.printf("%s ", base.data);
		for(Node<T> child: base.children){
			preOrder(child);
		}
	}
	
	/**
	 * Prints the post order traversal of the tree. Used to test
	 * the buildTree method.
	 * @param base root of the tree.
	 */
	public void postOrder(Node<T> base){
		for(Node<T> child: base.children){
			postOrder(child);
		}
		System.out.printf("%s ", base.data);
	}
	
	/**
	 * Prints the level order traversal of the tree using a
	 * linked list queue from the Java API.
	 * @param base root of the tree.
	 */
	public void levelOrder(Node<T> base){
		
		Node<T> q;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(base);
		
		while(!queue.isEmpty()){
			q =  queue.remove();
			System.out.printf("%s", q.data);
			
			for(Node<T> child: q.children){
				queue.add(child);
			}
			
			if(queue.size() == 0){
				System.out.printf(".\n");
			} else{
				System.out.printf(", ");
			}
		}
	}
	
	/**
	 * Sets mark fields in the tree to false in order
	 * to prepare for a query.
	 * @param base root of the tree.
	 */
	public void clearMarks(Node<T> base){
		base.unmark();
		for(Node<T> child: base.children){
			clearMarks(child);
		}
	}
	
	/**
	 * Handle's relationship requests for any two nodes in the tree and
	 * prints the result.
	 * @param nodeA Node 1
	 * @param nodeB Node 2
	 */
	public void request(Node<T> nodeA, Node<T> nodeB){
		Node<T> tempA = nodeA;
		Node<T> tempB = nodeB;
		int aCount = 0;
		int bCount = 0;
		
		//Mark all of A's ancestors.
		while(tempA != null){
			tempA.mark();
			tempA = tempA.parent;
		}
		
		//Find least common ancestor between A and B, and B's path length.
		while(!tempB.mark && tempB != null){
			tempB = tempB.parent;
			bCount++;
		}
		
		//Find path length of A to least common ancestor.
		tempA = nodeA;
		while(tempA != tempB){
			aCount++;
			tempA = tempA.parent;
		}
		
		//Determine relationship.
		switch(aCount){
		case 0:
			if(bCount == 0){
				System.out.printf("%s is %s.\n", nodeA.data, nodeB.data);
			} else if(bCount == 1){
				System.out.printf("%s is %s's parent.\n", nodeA.data, nodeB.data);
			} else if(bCount == 2){
				System.out.printf("%s is %s's grandparent.\n", nodeA.data, nodeB.data);
			} else if(bCount == 3){
				System.out.printf("%s is %s's great-grandparent.\n", nodeA.data, nodeB.data);
			} else{
				System.out.printf("%s is %s's (great)%d-grandparent.\n", nodeA.data, nodeB.data, bCount-2);
			}
			break;
		case 1:
			if(bCount == 0){
				System.out.printf("%s is %s's child.\n", nodeA.data, nodeB.data);
			} else if(bCount == 1){
				System.out.printf("%s is %s's sibling.\n", nodeA.data, nodeB.data);
			} else if(bCount == 2){
				System.out.printf("%s is %s's aunt/uncle.\n", nodeA.data, nodeB.data);
			} else{
				System.out.printf("%s is %s's (great)%d-aunt/uncle.\n", nodeA.data, nodeB.data, bCount-2);
			}
			break;
		case 2:
			if(bCount == 0){
				System.out.printf("%s is %s's grandchild.\n", nodeA.data, nodeB.data);
			} else if(bCount == 1){
				System.out.printf("%s is %s's niece/nephew.\n", nodeA.data, nodeB.data);
			} else{
				System.out.printf("%s is %s's %dth cousin %d times removed.\n", nodeA.data, nodeB.data, (Math.min(bCount,  aCount)-1), Math.abs(aCount - bCount));
			}
			break;
		default:
			if(bCount == 0){
				System.out.printf("%s is %s's (great)%d-grandchild.\n", nodeA.data, nodeB.data, aCount-2);
			} else if(bCount == 1){
				System.out.printf("%s is %s's (great)%d-niece/nephew.\n", nodeA.data, nodeB.data, aCount-2);
			}else{
				System.out.printf("%s is %s's %dth cousin %d times removed.\n", nodeA.data, nodeB.data, (Math.min(bCount,  aCount)-1), Math.abs(aCount - bCount));
			}
			break;
		}
		
		clearMarks(root);
	}
	
	public static void main(String args[]){
		
		proj2<String> tree = new proj2<>();
		
		Scanner sc = new Scanner(System.in);
		int orderSize;
		
		
		String pre = sc.nextLine();
		String post = sc.nextLine();
		
		String delims = "[\\s\\n,.]+";
		
		tree.pretrav = pre.trim().split(delims);
		tree.posttrav = post.trim().split(delims);
		
		tree.pretrav = Arrays.copyOfRange(tree.pretrav, 1, tree.pretrav.length);
		tree.posttrav = Arrays.copyOfRange(tree.posttrav, 1, tree.posttrav.length);
		
		orderSize = tree.pretrav.length;
		
		tree.root = tree.buildTree(orderSize, 0, 0);
		
		String query;
		while(sc.hasNextLine()){
			query = sc.nextLine();
			String[] request = query.split(delims);
			
			if(request[0].equals("?")){
				tree.request(tree.findNode(tree.root, request[1]), tree.findNode(tree.root, request[2]));
			} else{
				break;
			}
			
		}
		
		tree.levelOrder(tree.root);
		
		sc.close();
		
	}
}