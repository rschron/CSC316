package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single node in a tree.
 * 
 * @author Ryan
 *
 * @param <T>
 */
public class Node<T> {
  public T data;
  public Node<T> parent;
  public List<Node<T>> children;
  boolean mark;
	
  public Node(T data){
	  this.data = data;
	  this.children = new ArrayList<Node<T>>();
	  this.mark = false;
  }
  
  /**
   * Adds a child to the node.  Children are implemented via an ArrayList.
   * @param node child to add.
   */
  public void setChild(Node<T> node){
	  this.children.add(node);
  }
  
  /**
   * Sets the nodes parent.
   * @param parent of the node.
   */
  public void setParent(Node<T> parent){
	  this.parent = parent;
  }
  
  /**
   * Marks the node as visited.
   */
  public void mark(){
	  this.mark = true;
  }
  
  /**
   * Unmarks the node.
   */
  public void unmark(){
	  this.mark = false;
  }
}
