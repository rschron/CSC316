import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class proj1 {	
	static Node head = null;
		
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
		
	void addToFront(String s){
		Node newNode = new Node(s);
		newNode.next = head;
		head = newNode;
	}
	
	int getSize(){
		int i = 0;
		Node temp = head;
		
		while(temp != null){
			i++;
			temp = temp.next;
		}
		return i;
	}
	
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

	void printList(){
		Node temp = head;
		
		while(temp != null){
			System.out.println(temp.s);
			temp = temp.next;
		}
	}
	
	private class Node{
		String s;
		Node next;
			
		public Node(String s){
			this.s = s;
			next = null;
		}
	}
	
	public String processLine(String[] s){
	
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
            } else{
            	builder += s[i];
            }
		}
		return builder;
	}
		
	public void compressFile() throws IOException{
    	Scanner sc = new Scanner(System.in);
        String line;
        int bytes = 0;
        int finalBytes = 0;
        
        //Test for compressed
        line = sc.nextLine();
        String[] test = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
        if(test[0].equals("0")){
        	
        	  //Path for decompression
        	finalBytes += line.length();
        	test = Arrays.copyOfRange(test, 2, test.length);
        	String tBuilder = processLine(test);
        	bytes += tBuilder.length();
        	System.out.println(tBuilder);
        	
        	while(sc.hasNextLine()){
            	line = sc.nextLine();
            	finalBytes += line.length();
            	test = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
            	
            	//Check for end
            	if(test[0].equals("0")){
            		break;
            	}
            	
                tBuilder = processLine(test); 
                bytes += tBuilder.length();
                
                
              System.out.println(tBuilder);
                
            }
        	System.out.printf("0 Uncompressed: %d  Compressed: %d bytes\n", bytes, finalBytes);
        	sc.close();
        	
        } else{ 
        
        	//Path for compression
        	System.out.printf("0 ");
        	while(sc.hasNextLine()){
        		line = sc.nextLine();
        		bytes += line.length();
        		String[] split = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
        	
        		if(split[0].equals("0")){
        			break;
        		}
        	
        		String builder = processLine(split); 
        		finalBytes += builder.length();
            
            
        		System.out.println(builder);
            
        	}
        	System.out.printf("0 Uncompressed: %d  Compressed: %d bytes\n", bytes, finalBytes);
        	sc.close();
        
        }
    }
	
	
	
	
	public static void main(String[] args) {
        proj1 link = new proj1();
        
        try{
        	link.compressFile();
        } catch(IOException e){
        	e.printStackTrace();
        }
	}
}