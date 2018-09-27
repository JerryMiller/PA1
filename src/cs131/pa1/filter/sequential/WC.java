package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class WC extends SequentialFilter {
	
	String toGetCount;
	int charCount = 0;
	int wordCount = 0;
	int lineCount = 0;
	public WC() {
		input = new LinkedList<>();
		output = new LinkedList<>();
	}
	public void process(){
		if(input.isEmpty()) {
			output.add("0 0 0");
		}
		while (!input.isEmpty()){
			String line = input.poll();
			String processedLine = processLine(line);
			if (processedLine != null){
				output.add(processedLine);
			}
		}	
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if(line.equals(" ")) {
			charCount+=1;
			lineCount+=1;
			return null;
		}
		if(line.equals("")) {
			lineCount+=1;
			return null;
		}
		String[] words = line.split(" ");
		for(int i = 0; i < words.length; i++) {
			String word = words[i];
			charCount+=word.length();
		}
		wordCount+=words.length;
		lineCount+=1;
		if (input.isEmpty()) {
			return  lineCount + " " + wordCount + " " + charCount;
		}
		return null;
	}

}
