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
		this.fileName = fileName;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
//		StringBuilder str = new StringBuilder();
		File chosenFile = null;
		Scanner filereader = new Scanner(System.in);
//		line = line.trim(); //remove whitespace
		File currentDir = new File(System.getProperty("user.dir"));
		File[] filesList = currentDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory()) {
                continue;
            }
            else if(f.isFile()){
                if (fileName.equals(f.getName())){
                	chosenFile = f;
                	break;
                }
            }
        }
        if (chosenFile != null) {
        	
			try {
				filereader = new Scanner(chosenFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	while(filereader.hasNextLine()) {
        		output.add(filereader.nextLine());
        	}
        	filereader.close();
        }
//        if (next != null) {
//        	return str.toString();
        }
//        System.out.println(str.toString());
//        return null;

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	}


