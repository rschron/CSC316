package source;

public class UpTree {
	
	int[] tree = new int[1000];
	int components = 0;
	
	public int union(int root1, int root2){
		if(tree[root1] >= tree[root2]){
			tree[root1] += tree[root2];
			tree[root2] = root1;
			components--;
			return root1;
		} else{
			tree[root2] += tree[root1];
			tree[root1] = root2;
			components--;
			return root2;
		}
	}
	
	public int find(int val){
		while(tree[val] >= 0){
			val = tree[val];
		}
		return val;
	}
	
	public void makeSet(int val){
		if(tree[val] != -1){
			tree[val] = -1;
			components++;
		}
	}
	
	public int getComponents(){
		return components;
	}
	
	public boolean isRoot(int i){
		return tree[i] <= -1;
	}
	
	public int getSize(int i){
		return tree[find(i)];
	}
	
	public int getSetSize(){
		return tree.length;
	}
}