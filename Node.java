package tree;

import java.util.ArrayList;
import java.util.List;

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
  
  public void setChild(Node<T> node){
	  this.children.add(node);
  }
  
  public void setParent(Node<T> parent){
	  this.parent = parent;
  }
  
  public void mark(){
	  this.mark = true;
  }
  
  public void unmark(){
	  this.mark = false;
  }
  
  
}
