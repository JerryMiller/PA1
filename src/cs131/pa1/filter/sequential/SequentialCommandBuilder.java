package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		String[] originalLine = command.split("\\|");
		List<SequentialFilter> allFilters = new ArrayList<SequentialFilter>();
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
		String[] filters = subCommand.split(" ");
		SequentialFilter filter = null;
		for(int i = 0; i<filters.length; i++) {
			
			if(filters[i].equals("cat")){
				filter = new Cat(filters[i+1]);
				filter.input.add(filters[i]);
				i++;
			}
			else if(filters[i].equals("pwd")) {
				filter = new Pwd();
				System.out.print(filter.processLine("")+ "\n");
			}
			else if(filters[i].equals("ls")) {
				filter = new LS();
				System.out.print( filter.processLine(""));
			}
			else if(filters[i].equals("cd")) {
				filter = new Cd(filters[i+1]);
				
				i++;
				
			}
			else if(filters[i].equals("wc")) {
				filter = new WC(filters[i+1]);
				filter.input.add(filters[i]);
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
