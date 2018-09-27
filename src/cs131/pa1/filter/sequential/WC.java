package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class WC extends SequentialFilter {
	
	String fileName;
	public WC(String file) {
		fileName = file;
		input = new LinkedList<>();
		output = new LinkedList<>();
		input.add(file);
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		int charCount = 0;
		int wordCount = 0;
		int lineCount = 0;
		
		File chosenFile = null;
		line = line.trim(); //remove whitespace
		File currentDir = new File(".");
		File[] filesList = currentDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory()) {
                continue;
            }
            else if(f.isFile()){
                if (line.equals(f.getName())){
                	chosenFile = f;
                	break;
                }
            }
        }
        Scanner filereader = null;
        if (chosenFile != null) {
        	try {
				filereader = new Scanner(chosenFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	while (filereader.hasNextLine()) {
        		String fileLine = filereader.nextLine();
        		String[] words = line.split(" ");
        		for(int i = 0; i < words.length; i++) {
        			String word = words[i];
        			if(word.equals(" ") || word.equals("")) {
        				continue;
        			}
        			charCount+=word.length();
        		}
        		wordCount+=words.length;
        		lineCount+=1;
        		
        		
        	}
        }

		String finalCount = lineCount + " " + wordCount + " " + charCount;
		if (next != null) {
			filereader.close();
			return finalCount;

		}
		System.out.println(finalCount);
		return null;
	}

}
