package source;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpellChecker {	

	public String combine(String[] words){
		String newString = "";
		for(int i = 0; i < words.length; i++){
			newString += words[i];
			newString += " ";
		}
		return newString.substring(0, newString.length()-1);
	}
	
	public static void main(String[] args) {
		//SpellChecker s = new SpellChecker();
		Hash hashTable = new Hash();
		
		//int probes = 0;
		int wordsInDict = 0;
		int inputWordCount = 0;
		int mispelledCount = 0;
		
		//Read in word data base
		String line;
		String[] words;
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				words = line.split("[-\\s]");
				for(int i = 0; i < words.length; i++){
					hashTable.insert(words[i]);
					wordsInDict++;
				}
			}
			
			
			br.close();
			fr.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		//Start checking the input file
		try{
			fr = new FileReader(args[1]);
			br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(args[2]);
			BufferedWriter bw = new BufferedWriter(fw);
			
			while((line = br.readLine()) != null){
				words = line.split("[-\\s]");
				for(int i = 0; i < words.length; i++){
					//Do spell checking
					inputWordCount++;
					
					if(hashTable.lookup(words[i]) >= 0){
						continue;
					} else if(words[i].substring(0,1).equals(words[i].substring(0,1).toUpperCase())){  //Check for capitalized word
						if(hashTable.lookup(words[i].substring(0,1).toLowerCase() + words[i].substring(1)) >= 0){
							continue;
						} else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else if(words[i].substring(words[i].length()-2).equals("'s")){  //Check for conjunction
						if(hashTable.lookup(words[i].substring(0, words[i].length()-2)) >= 0){
							continue;
						} else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else if(words[i].charAt(words[i].length()-1) == 's'){   //Check for plural
						if(hashTable.lookup(words[i] = words[i].substring(0, words[i].length()-1)) >= 0){
							continue;
							
						}  else if(words[i].substring(words[i].length()-2).equals("es")){   //Secondary check for 'es'
							if(hashTable.lookup(words[i].substring(0, words[i].length()-2)) >= 0){
								continue;
							} else{
								bw.write(words[i] + " ");
								mispelledCount++;
							}
						}
					} else if(words[i].substring(words[i].length() - 2).equals("ed")){ //Check for "ed"
						if(hashTable.lookup(words[i].substring(0, words[i].length() - 2)) >= 0){
							continue;
						} else if(hashTable.lookup(words[i].substring(0, words[i].length() - 1)) >= 0){
							continue;
						} else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else if(words[i].substring(words[i].length() - 2).equals("er")){ //Check for "er"
						if(hashTable.lookup(words[i].substring(0, words[i].length() - 2)) >= 0){
							continue;
						} else if(hashTable.lookup(words[i].substring(0, words[i].length() - 1)) >= 0){
							continue;
						} else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else if( words[i].length() >= 3 && words[i].substring(words[i].length() - 3).equals("ing")){ //Check for "ing"
						if(hashTable.lookup(words[i].substring(0, words[i].length() - 3)) >= 0){
							continue;
						} else if(hashTable.lookup(words[i].substring(0, words[i].length() - 3).concat("e")) >= 0){
							continue;
						} else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else if(words[i].substring(words[i].length() - 2).equals("ly")){   //Check for "ly"
						if(hashTable.lookup(words[i].substring(0, words[i].length() - 2)) >= 0){
							continue;
						}else{
							bw.write(words[i] + " ");
							mispelledCount++;
						}
					} else{
					
					bw.write(words[i] + " ");
					mispelledCount++;
					}
				}
				bw.newLine();
			}
			
			//Print report
			bw.newLine();
			bw.write("Number of words in dictionary: " + wordsInDict);
			bw.newLine();
			bw.write("Number of words to spell check: " + inputWordCount);
			bw.newLine();
			bw.write("Number of mispelled words: " + mispelledCount);
			bw.newLine();
			bw.write("Total probes: " + hashTable.probes);
			bw.newLine();
			bw.write("Average probes per word: " + ((double)hashTable.getProbes()/(double)inputWordCount));
			bw.newLine();
			bw.write("Average probes per lookup: " + ((double)hashTable.getProbes()/(double)hashTable.getLookups()));
			
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
}
