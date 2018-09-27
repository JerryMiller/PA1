package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Validator;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		String[] originalLine = command.split("\\|");
//		ArrayList<String> arrayList = new ArrayList<String>();
//		String[] temp = new String[firstLine.length];
//		for(int i=0; i<firstLine.length; i++) {
//			temp = firstLine[i].split(" ");
//			for(int j=0; j<temp.length; j++) {
//				arrayList.add(temp[j]);
//			}
//			
//			
//		}
//		String[] originalLine = arrayList.toArray(new String[arrayList.size()]);
//		for(int i =0; i<originalLine.length; i++) {
//			System.out.print(originalLine[i]+i);
//		}
		List<SequentialFilter> allFilters = new ArrayList<SequentialFilter>();
		SequentialFilter printer = new Printer();
		allFilters.add(0,printer);
		for(int i = originalLine.length-1; i>=0; i--) {
			
			SequentialFilter filter = constructFilterFromSubCommand(originalLine[i]);
			allFilters.add(0, filter);
		
		}
		
		
		linkFilters(allFilters);
		
		return allFilters;
	}
	/* I didn't use these next two classes because I think that the way I did is better */
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	
	
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		subCommand = subCommand.trim();
		String[] filters = subCommand.split(" ");
		
		
//		String[] filters = new String[initialFilters.length-1];
//		if(initialFilters.length>1) {
//			
//			
//		if(initialFilters[0].isEmpty()) {
//			filters = new String[initialFilters.length-1];
//			for(int i =0; i<filters.length; i++) {
//				filters[i] = initialFilters[i+1];
//			
//			}
//		}else {
//			filters = new String[initialFilters.length];
//			for(int i =0; i<initialFilters.length; i++) {
//				filters[i] = initialFilters[i];
//			}
//		}
//		}
//		
//		for(int i =0; i<filters.length; i++) {
//			System.out.print(filters[i]+i);
//		
//		}
//		}else {
//			filters = subCommand.split(" ");
//		}
		
		SequentialFilter filter = null;
		for(int i = 0; i<filters.length; i++) {
			
			if(filters[i].equals("cat")){
				filter = new Cat(filters[i+1]);
//				filter.input.add(filters[i]);
				i++;
			}
			else if(filters[i].equals("pwd")) {
				filter = new Pwd();
			}
			else if(filters[i].equals("ls")) {
				filter = new LS();
//				System.out.print( filter.processLine(""));
			}
			else if(filters[i].equals("cd")) {
				filter = new Cd(filters[i+1]);
				
				i++;
				
			}
			else if(filters[i].equals("wc")) {
				filter = new WC(filters[i+1]);
				filter.input.add(filters[i]);
				i++;
			}else if(filters[i].equals("grep")){
				filter = new grep(filters[i+1]);
				i++;
				
			}
			else {
				System.out.println("Command not valid");
				System.exit(0);
			}
			
		}
		
		return filter;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		for(int i =0; i<filters.size()-1; i++) {
			filters.get(i).setNextFilter(filters.get(i+1));
			filters.get(i+1).setPrevFilter(filters.get(i));
		}
		
		
		
		return false;
	}
}
