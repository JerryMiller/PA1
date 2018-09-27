package cs131.pa1.filter.sequential;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Unique extends SequentialFilter {
	
	public Unique() {
		input = new LinkedList<>();
		output = new LinkedList<>();
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if(!output.contains(line)) {
			output.add(line);
		}
		return null;
	}

}
