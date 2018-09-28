package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class Cat extends SequentialFilter{
	String fileName = null;
	String[] fileNames;
	public Cat(String fileName) {
		this.fileName = fileName;
		input = new LinkedList<>();
		output = new LinkedList<>();
		this.fileName = fileName;
	}
	
	public Cat (String[] fileString) {
		this.fileNames= fileString;
		input = new LinkedList<>();
		output = new LinkedList<>();
	}
	
	public String getName() {
		return this.fileName;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
//		StringBuilder str = new StringBuilder();
		String fileNameString = "";
		
		if(fileNames !=null && fileNames.length>0 && SequentialREPL.error==false) {
			for(int i=0; i<fileNames.length; i++) {
				fileNameString += fileNames[i]+" "; 
			}
			
			for(int i =0; i<fileNames.length; i++) {
				fileName = fileNames[i];
				File chosenFile = null;
				Scanner filereader = new Scanner(System.in);
//				line = line.trim(); //remove whitespace
				File currentDir = new File(SequentialREPL.currentWorkingDirectory);
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
		        }else {
		        	
		         	   System.out.print(Message.FILE_NOT_FOUND.with_parameter("cat "+fileNameString));
		         	   SequentialREPL.error= true;
		        }
			}
		}else {
		File chosenFile = null;
		Scanner filereader = new Scanner(System.in);
//		line = line.trim(); //remove whitespace
		File currentDir = new File(SequentialREPL.currentWorkingDirectory);
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
        }else {
        	
         	   System.out.print(Message.FILE_NOT_FOUND.with_parameter("cat "+fileName));
         	   SequentialREPL.error= true;
        }
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


