package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Cat extends SequentialFilter{
	String fileName = null;
	public Cat(String fileName) {
		this.fileName = fileName;
		input = new LinkedList<>();
		output = new LinkedList<>();
		input.add(fileName);
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		File chosenFile = null;
		line = line.trim(); //remove whitespace
		File currentDir = new File(System.getProperty("user.dir"));
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
        if (chosenFile != null) {
        	Scanner filereader;
			try {
				filereader = new Scanner(chosenFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
        	while(filereader.hasNextLine()) {
        		str.append(filereader.nextLine());
        		str.append("\n");
        	}
        	filereader.close();
        }
        if (next != null) {
        	return str.toString();
        }
        System.out.println(str.toString());
        return null;
	}

}
