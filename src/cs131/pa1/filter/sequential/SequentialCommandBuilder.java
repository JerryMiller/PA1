package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import cs131.pa1.filter.Message;

//import javax.xml.bind.Validator;

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
		String str = originalLine[originalLine.length-1];
		if(str.contains(">")) {
			int index = str.indexOf(">");
			String newCommand = str.substring(0, index);
			if(newCommand == null || newCommand.equals("")) {
				System.out.print(Message.REQUIRES_INPUT.with_parameter("> " + str.substring(index + 1, str.length()).trim()));
				return null;
			}
			originalLine[originalLine.length-1] = newCommand;
			printer = new Printer(str.substring(index + 1, str.length()).trim());
		}
		for(String st : originalLine) {
			if (st.contains(">")) {
				int index = st.indexOf(">");
				String newCommand = st.substring(0, index);
				System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter("> " + st.substring(index + 1, st.length()).trim()));
				return null;
			}
		}
		allFilters.add(0,printer);
		for(int i = originalLine.length-1; i>=0; i--) {
			
			SequentialFilter filter = constructFilterFromSubCommand(originalLine[i]);
			if(filter == null) {
				return null;
			}else {
			allFilters.add(0, filter);
			}
		
		}
		
		
		linkFilters(allFilters);
		
//		for(SequentialFilter f : allFilters) {
////			if(f instanceof grep && f.input.isEmpty()) {
////				grep j = (grep) f;
////				System.out.print(Message.REQUIRES_INPUT.with_parameter("grep " + j.getWord()));
////				return null;
////         }else 
////			if( f instanceof WC && f.input.isEmpty()) {
////				System.out.print(Message.REQUIRES_INPUT.with_parameter("wc"));
////				return null;
//				if( f instanceof Unique && f.input.isEmpty()) {
//				System.out.print(Message.REQUIRES_INPUT.with_parameter("uniq"));
//				return null;
//			}
//		}
		
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
				if(filters.length==1) {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(filters[i]));
					return null;
					
				}else if(filters.length>2) {
					
					String[] fullString = new String[filters.length-1];
					for(int j = 1; j<filters.length; j++) {
						fullString[j-1] = filters[j];
						i++;
					}
					filter = new Cat(fullString);
				}else {				
					filter = new Cat(filters[i+1]);
					i++;
				}
				i++;
				
			
			}else if(filters[i].equals("pwd")) {
				filter = new Pwd();
			}
			else if(filters[i].equals("ls")) {
				filter = new LS();
//				System.out.print( filter.processLine(""));
			}
			else if(filters[i].equals("cd")) {
				if(filters.length<2) {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
				}else {
				filter = new Cd(filters[i+1]);
				i++;
				}
			}
			else if(filters[i].equals("wc")) {
				filter = new WC();
				i++;
			}else if(filters[i].equals("grep")){
				if(filters.length==1) {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(filters[i]));
					return null;
					
				}else {
				
				filter = new grep(filters[i+1]);
				i++;
				}
				
			}else if(filters[i].equals("uniq")) {
				filter = new Unique();
				i++;
			}
			else {
				String s="";
				for(int j=0; j<filters.length; j++) {
					s += filters[j]+" ";
				}
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(s));
				return null;
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
