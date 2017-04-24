package source;

public class Hash {

	int hashSize = 30871;  //Prime chosen that is 20-30% larger than expected hash table size
	Node[] hashTable = new Node[hashSize];
	int probes = 0;
	int numLookups = 0;
	
	public int hashFunc(String word){
		//Cyclic shift hash
		int hashCode = 0;
		for(int i = 0; i < word.length(); i++){
			char letter = word.charAt(i);
			int val = (int) letter;
			hashCode += val * Math.pow(128, i);
		}
		
		//Hash compression by division
		hashCode %= hashSize;
		return hashCode;
	}
	
	public int getProbes(){
		return probes;
	}
	
	public int getLookups(){
		return numLookups;
	}
	
	public Hash(){
		for(int i = 0; i < hashSize; i++){
			hashTable[i] = null;
		}
	}
	
	public void insert(String word){
		int code = hashFunc(word);
		
		if(hashTable[code] != null){
			Node ptr = hashTable[code];
			while(ptr.next != null){
				ptr = ptr.next;
			}
			ptr.next = new Node(word);
		} else{
			hashTable[code] = new Node(word);
		}
	}
	
	public int lookup(String word){
		int key = hashFunc(word);
		numLookups++;
		
		if(hashTable[key] == null){
			return -1;
		} else{
			Node ptr = hashTable[key];
			while(ptr != null){
				probes++;
				if(ptr.word.equals(word)){
					return key;
				}
				ptr = ptr.next;
			}
			return -1;
		}
	}
	
	public void printHash(){
		for(int i = 0; i < hashTable.length; i++){
			Node ptr = hashTable[i];
			System.out.printf("%d. ", i);
			while(ptr != null){
				System.out.printf("%s ", ptr.word);
				ptr = ptr.next;
			}
			System.out.printf("\n");
		}
		
	}
	
	/**
	 * Nodes in the hash chains.  Holds a word.
	 * @author Ryan
	 */
	public class Node{
		String word;
		Node next;
		
		public Node(String word){
			this.word = word;
			next = null;
		}
	}
}
