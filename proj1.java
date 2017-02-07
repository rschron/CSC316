
import java.io.IOException;
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
	
	public void compressFile() throws IOException{
    	Scanner sc = new Scanner(System.in);
        String line;
        int bytes = 0;
        
        System.out.printf("0 ");
        
        while(sc.hasNextLine()){
        	line = sc.nextLine();
            String[] split = line.split("(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
            String builder = "";
            
            for(int i = 0; i < split.length; i++){
                if(split[i].matches("[a-zA-Z0-9]+")){
                	int pos = findPosition(split[i]);
                	
                	if(pos < 0){
                		addToFront(split[i]);
                	} else{
                		split[i] = String.valueOf(pos);
                		String temp = remove(pos);
                		addToFront(temp);
                	}
                	bytes += split[i].length();
                	builder += split[i];
                } else{
                	builder += split[i];
                	bytes++;
                }
            }       
          System.out.println(builder);
            
       }
        System.out.printf("0 Compressed: %d bytes\n", bytes);
        sc.close();
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
