package cs131.pa1.filter.sequential;

import java.util.LinkedList;

public class grep extends SequentialFilter{
	
	private String word;

	public grep(String grepWord) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		this.word = grepWord;
		
	}
	
	@Override
	protected String processLine(String line) {
		while(!input.isEmpty()) {
			String sentence = input.poll();
			if(sentence.contains(this.word)) {
				output.add(sentence);
			}
		}
		return null;
	}

}
