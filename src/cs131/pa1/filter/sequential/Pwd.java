package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Pwd extends SequentialFilter{
	
	
	
	@Override
	public String processLine(String line) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		
		
		String directory = "";
		directory = SequentialREPL.currentWorkingDirectory;//new java.io.File(".").getCanonicalPath();
		
		return directory;
	}
}
