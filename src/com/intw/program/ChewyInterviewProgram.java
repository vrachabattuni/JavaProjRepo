package com.intw.program;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChewyInterviewProgram {

	/**
	 *  Requirement:
	 * 
	 * Given the list of sentences
	 * Get count of each word and the line number the word is present
	 * 
	 * Use case
	 * Example: String list
	 * "Get up early in the morning"
	 * "Have breakfast in the morning"
	 * "Brush your teeth"
	 * "Get dressed for the work", "Do your work");
	 * Output: Word: no of times word appear in the whole list :: Set of line numbers the word has appeared
	 *  (assumption: Line number starts from 0)
	 *  in : 2 :: [0, 1]  work : 2 :: [3, 4]  for : 1 :: [3]  Have : 1 :: [1]
        dressed : 1 :: [3]  your : 2 :: [2, 4]  Do : 1 :: [4]  Brush : 1 :: [2]
        morning : 2 :: [0, 1]  the : 3 :: [0, 1, 3]  Get : 2 :: [0, 3] teeth : 1 :: [2]
        up : 1 :: [0] breakfast : 1 :: [1]  early : 1 :: [0]

	 *  
	 *  Architecture and Design
	 *  I/P: List of Strings
	 *  O/P: Map with 
	 *       key:    word: String,  
	 *       value:  object with data variables word: String, wordCount: int, lineNumberSet: List
	 *       
	 */



	List<String> strings =  Arrays.asList("Get up early in the morning", 
			                              "Have breakfast in the morning",
			                              "Brush your teeth",
			                              "Get dressed for the work",
			                              "Do your work");

	
	public static void main(String[] args) {
		ChewyInterviewProgram cp = new ChewyInterviewProgram();
		Map<String, WordModel> hMap =  cp.wordService();
		//print output
		hMap.entrySet().stream().forEach(es -> System.out.println(es.getKey()+ " : " + 
				es.getValue().getWordCount() + " :: " +
				es.getValue().getLineNumberSet().toString()));
	}

	private final class WordModel{
		String word;
		Set<Integer> lineNumberSet;
		int wordCount;
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public Set<Integer> getLineNumberSet() {
			return lineNumberSet;
		}
		public void setLineNumberSet(Set<Integer> lineNumberSet) {
			this.lineNumberSet = lineNumberSet;
		}
		public int getWordCount() {
			return wordCount;
		}
		public void setWordCount(int wordCount) {
			this.wordCount = wordCount;
		}		public WordModel(String word, int wordCount, Set<Integer> lineNumberSet) {
			this.word = word;
			this.wordCount = wordCount;
			this.lineNumberSet = lineNumberSet;
		}
	}
	

	public final Map<String, WordModel> wordService() {
		Map<String, WordModel> hMap =  new HashMap<>(); 
		// output HashMap collects the data
		IntStream.range(0, strings.size())   //stream1
		.map(i-> {			
			       Arrays.stream(strings.get(i).split(" "))  //stream2
			       .map(str-> {
				            if(hMap.get(str) == null){					
					           hMap.put(str, new WordModel(str,0, new HashSet<>()));
				             }
				             hMap.get(str).setWordCount(hMap.get(str).getWordCount() + 1);		        	        	       
				             hMap.get(str).getLineNumberSet().add(i);
				            //dummy return for map operation 
				             return str;
			                })
			       .collect(Collectors.joining(",")); //dummy terminal operation
			       //dummy return for map operation 
			       return i;
		         })				
		.reduce(1, (i1,i2)->i1+i2); //dummy terminal operation 
		return hMap;
	}

}
