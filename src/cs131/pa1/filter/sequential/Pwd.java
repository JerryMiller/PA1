package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Pwd extends SequentialFilter{
	
	@Override
	public void process() {
		String directory = "";
		directory = SequentialREPL.currentWorkingDirectory;
		output.add(directory);
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
