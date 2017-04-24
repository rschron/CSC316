package source;
/**
 * This class handles the hash table data structure.  It uses Seperate Chaining to handle
 * collisions.
 * @author RSC
 *
 */
public class Hash {

	int hashSize = 30871;  //Prime chosen that is 20-30% larger than expected hash table size
	Node[] hashTable = new Node[hashSize];
	int probes = 0; //Counter for probes in the table
	int numLookups = 0; //Counter for number of lookup operations performed
	
	/**
	 * Hash function that translates a string into a hash code.  The function uses a Cyclic Shift mechanism
	 * to generate a hash code, and then compresses the code to fit in the hash table by using division.
	 * @param word String to add to the hash table
	 * @return int hash code.
	 */
	public int hashFunc(String word){
		//Cyclic shift hash
		long hashCode = 0;
		for(int i = 0; i < word.length(); i++){
			hashCode*=128;
			hashCode += (long)word.charAt(i);
		}
		//Compression by division
		return (int) (Math.abs(hashCode) % hashSize);
	}
	
	/**
	 * Returns the number of probes.
	 * @return int
	 */
	public int getProbes(){
		return probes;
	}
	
	/**
	 * Returns the number of lookups performed.
	 * @return int
	 */
	public int getLookups(){
		return numLookups;
	}
	
	/**
	 * Constructor that initializes all locations in the hash table to null.
	 */
	public Hash(){
		for(int i = 0; i < hashSize; i++){
			hashTable[i] = null;
		}
	}
	
	/**
	 * Inserts a string into the hash table.  The table handles collisions by Separate Chaining.
	 * @param word String to insert.
	 */
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
	
	/**
	 * Finds a string in the hash table using its hash code.
	 * @param word String to find
	 * @return int -1 if no match, otherwise the hash code is returned.
	 */
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
	
	/**
	 * Prints the hash table in order to assist with debugging.
	 */
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
	 * Nodes in the hash chains.  Holds a word and a reference to the next node.
	 * @author Ryan Schron
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