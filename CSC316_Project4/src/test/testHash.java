package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import source.Hash;
import source.Hash.Node;

public class testHash{
	Hash hashTable = new Hash();
	
	@Test
	public void testOperations(){
		String line;
		String[] words;
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader("C:/Users/Ryan/workspace/CSC316_Project4/src/dict.txt");
			br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				words = line.split("[-\\s]");
				for(int i = 0; i < words.length; i++){
					hashTable.insert(words[i]);
				}
			}
			
			
			br.close();
			fr.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		hashTable.printHash();
		
		assertEquals(-1, hashTable.lookup("lkjyhkljhlkjy"));
	}
	
}
