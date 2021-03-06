package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class Cd extends SequentialFilter{
	
	private String directoryName;
	
	public Cd(String directoryName) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		this.directoryName = directoryName;
		
	}
	public String getName() {
		return this.directoryName;
	}
	public String processLine(String line) {
		return null;
		
	}
	public void process() {
		String directoryName = this.directoryName;
		if(directoryName.equals("..")) {
			String[] directNames = SequentialREPL.currentWorkingDirectory.split(Filter.FILE_SEPARATOR);
			int lastSlashIndex = SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR);
			int prevDirectory = directNames.length-1;
			String directory = "";
			int i = 0;
			while(i < prevDirectory) {
				directory+= directNames[i] + Filter.FILE_SEPARATOR;
				i++;
			}
			int lastSlash = directory.lastIndexOf(Filter.FILE_SEPARATOR);
			directory = directory.substring(0,lastSlash);
			SequentialREPL.currentWorkingDirectory = directory;
			
		}else if (directoryName.equals(".")) {
			
			
		}else{
			File file = new File(SequentialREPL.currentWorkingDirectory+Filter.FILE_SEPARATOR+directoryName);
			if(file.exists()) {
				SequentialREPL.currentWorkingDirectory += Filter.FILE_SEPARATOR + directoryName;
			}else {
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("cd "+ directoryName));
			}
			
			
		}
		
		
		
	}
	

}
