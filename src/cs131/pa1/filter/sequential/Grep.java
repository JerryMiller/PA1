package cs131.pa1.filter.sequential;

import java.util.LinkedList;

public class Grep extends SequentialFilter{
	
	public static String word;

	public Grep(String grepWord) {
//		input = new LinkedList<>();
//		output = new LinkedList<>();
		this.word = grepWord;
		
	}
	public String getWord() {
		return this.word;
	}
	
	@Override
	protected String processLine(String line) {
		
//			String sentence = input.poll();
			if(line.contains(this.word)) {
				output.add(line);
			}
		
		return null;
	}

}
