
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class will read input from stdin and output to stdout either
 * a compressed or decompressed version of the input depending on the initial 
 * input state.  In the case of compression, it will also provide an analysis
 * of the efficacy of the compression algorithm.  The algorithm employed by
 * this program is a move to front algorithm implemented using a linked list.
 * @author RSC
 */

public class proj1 {
	/** A reference to the front of the linked list **/
	static Node head = null;
	
	/**
	 * The method will take an input string and search for it in the
	 * linked list.  If found, it will return the position of the string, otherwise
	 * it will return -1.
	 * @param s String to look for.
	 * @return int position in list.
	 */
	int findPosition(String s){
		Node temp = head;
		int count = 1;
		
		while(temp != null){
			if(temp.s.equals(s)){
				return count;
			} else{
				temp = temp.next;
				count++;
			}
		}
		
		return -1;
	}
	
	/**
	 * Creates a node for the linked list with the given string and
	 * adds it to the front of the list.
	 * @param s String to add to the list.
	 */
	void addToFront(String s){
		Node newNode = new Node(s);
		newNode.next = head;
		head = newNode;
	}
	
	/**
	 * Returns the current size of the list.  This method is used for testing
	 * purposes.
	 * @return int size of linked list.
	 */
	int getSize(){
		int i = 0;
		Node temp = head;
		
		while(temp != null){
			i++;
			temp = temp.next;
		}
		return i;
	}
	
	/**
	 * Removes a node from the list at the given position.
	 * @param pos position of node to remove.
	 * @return String item in the removed node.
	 */
	String remove(int pos){
		Node prev = head;
		
		if(pos == 1){
			head = prev.next;
			prev.next = null;
			return prev.s;
		} else{
		
			for(int i = 1; i < pos-1; i++){
				prev = prev.next;
			}
			Node curr = prev.next;
			prev.next = curr.next;
			curr.next = null;
		
			return curr.s;
		}
	}

	/**
	 * Prints the entire list.  Method is used for testing.
	 */
	void printList(){
		Node temp = head;
		
		while(temp != null){
			System.out.println(temp.s);
			temp = temp.next;
		}
	}
	
	/**
	 * Inner class that represents a single node in the list.
	 * @author RSC
	 *
	 */
	private class Node{
		String s;
		Node next;
		
		/**
		 * Creates a node with the given string.
		 * @param s string to populate the node with.
		 */
		public Node(String s){
			this.s = s;
			next = null;
		}
	}
	
	/**
	 * Takes an array of strings as an arg and adds words to the list as needed. In
	 * the case the word is already in the list, the item will be replaced by its position
	 * in the list, thereby compressing the string.  The new, compressed string is returned.
	 * @param s string array to compress.
	 * @return compressed string.
	 */
	public String compressLine(String[] s){
		
		String builder = "";
		
		for(int i = 0; i < s.length; i++){
            if(s[i].matches("[a-zA-Z0-9]+")){
            	int pos = findPosition(s[i]);
            	
            	if(pos < 0){
            		addToFront(s[i]);
            	} else{
            		
            		s[i] = String.valueOf(pos);
            		String temp = remove(pos);
            		addToFront(temp);
            	}
            	builder += s[i];
            } else{
            	builder += s[i];
            }
		}
		return builder;
	}
	
	/**
	 * Takes a string array as an arg from a compressed line, and performs decompression.
	 * If a number is encountered, that string at that position is moved to the front of the
	 * list and the number is replaced with the string. 
	 * @param s string array to decompress.
	 * @return decompressed string.
	 */
	public String decompressLine(String[] s){
			
			String builder = "";
			
			for(int i = 0; i < s.length; i++){
				
				if(s[i].matches("[0-9]+")){
					int pos = Integer.parseInt(s[i]);
					String temp = remove(pos);
					addToFront(temp);
					builder += temp;
					
				} else if(s[i].matches("[a-zA-Z]+")){
					addToFront(s[i]);
	            	builder += s[i];
	            } else{
	            	builder += s[i];
	            }
			}
			return builder;
		}
	
	/**
	 * Reads input from stdin by line, and splits the line into substrings.  This array
	 * of strings is then passed to the appropriate function for compression or decompression,
	 * and then written to stdout.  Method also performs compression efficacy analysis.
	 * @throws IOException
	 */
	public void processFile() throws IOException{
    	Scanner sc = new Scanner(System.in);
        String line;
        long uncompressedBytes = 0;
        long compressedBytes = 0;
        
      //Test for compressed
        line = sc.nextLine();
        String[] test = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
        if(test[0].equals("0")){
        	
        	  //Path for decompression
        	test = Arrays.copyOfRange(test, 2, test.length);
        	String tBuilder = decompressLine(test);
        	System.out.print(tBuilder);
        	
        	while(sc.hasNextLine()){
            	line = sc.nextLine();
            	test = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
            	
            	//Check for end
            	if(test[0].equals("0")){
            		break;
            	}
              System.out.println();
              tBuilder = decompressLine(test); 
              System.out.print(tBuilder);
                
            }
        	sc.close();
        	
        } else{ 
        
        	//Path for compression
        	System.out.printf("0 ");
        	uncompressedBytes += line.length();
        	String builder = compressLine(test);
        	compressedBytes += builder.length();
        	System.out.println(builder);
        	
        	while(sc.hasNextLine()){
        		line = sc.nextLine();
        		uncompressedBytes += line.length();
        		String[] split = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
        	
        		builder = compressLine(split); 
        		compressedBytes += builder.length();
            
            
        		System.out.println(builder);
            
        	}
        	System.out.printf("0 Uncompressed: %d bytes;  Compressed: %d bytes\n", uncompressedBytes, compressedBytes);
        	sc.close();
        
        }
    }
	
	public static void main(String[] args) {
        proj1 link = new proj1();
        
        try{
        	link.processFile();
        } catch(IOException e){
        	e.printStackTrace();
        }
	}
}