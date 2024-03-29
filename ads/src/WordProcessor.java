/*
	Developed for the University of Glasgow
	Algorithms and Data Structures exercise spring 2018.
	Student ID: 2352426
	@author Nicholas Ferrara
*/

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		String returnString = "";
		int elInLine = 0;
		for (E el : inputSet) {
			returnString += el + ", ";
			elInLine++;
			if (elInLine >= 5) {
				elInLine = 0;
				returnString += System.getProperty("line.separator");
			}
		}
		return returnString;
	}

	public static void main(String[] args) {
		//create a set of type String called wordSet
		TreeSet<String> wordSet = new TreeSet<String>();
		//create a set of type CountedElement<String> called countedWordSet 
		TreeSet<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();
		
		final int NUM_ARGS = 3;
		//for each input file (assume 3 arguments, each the name of a file)
		for (int i = 0; i < NUM_ARGS; i++) {
			String fileName = args[i];
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                if (!wordSet.contains(line)) {
	                	wordSet.add(line);
	                	countedWordSet.add(new CountedElement<String>(line));
	                }
	                else {
	                	Iterator<CountedElement<String>> iterator = countedWordSet.iterator();
	                    while(iterator.hasNext()) {
	                    	CountedElement<String> el = iterator.next();
	                        if(el.getElement().equals(line)) {
                				el.setCount(el.getCount() + 1);
                				break;
	                        }
	                    }
	                }
	            }
	            
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            ex.printStackTrace();
	        }
		}
		

	System.out.println(displaySet(countedWordSet));

	}
}
