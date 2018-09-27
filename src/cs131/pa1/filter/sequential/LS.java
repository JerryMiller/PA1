package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

public class LS extends SequentialFilter {
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		input = new LinkedList<>();
		output = new LinkedList<>();
		StringBuilder str = new StringBuilder();
		File currentDir = new File(SequentialREPL.currentWorkingDirectory);
		File[] filesList = currentDir.listFiles();
        for(File f : filesList){
        	str.append(f.getName() + "\n");
        }
		return str.toString();
	}

}
