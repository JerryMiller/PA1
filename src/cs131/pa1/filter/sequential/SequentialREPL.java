package cs131.pa1.filter.sequential;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	static boolean error = false;
	
	public static void main(String[] args){
		
		File file = new File("."); // Gets the current working directory
		try {
			currentWorkingDirectory = file.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message newCommand = Message.NEWCOMMAND; 
		Message welcome = Message.WELCOME;
		System.out.print(welcome); // Prints out the welcome line
		String input = "";
		SequentialCommandBuilder commands = new SequentialCommandBuilder(); //Used to build the commands (used in the loop below)
		Scanner reader = new Scanner(System.in); // Reads user input
		SequentialFilter filter = null;
		System.out.print(newCommand.toString());
		input = reader.nextLine(); // Reads user input
		while(!input.equals("exit")){ //While the user has not entered "exit"
			List<SequentialFilter> finalList = commands.createFiltersFromCommand(input); //create the list of commands from the user input and 
			if(finalList != (null)) {
				error=false;
			for(int i = 0; i<finalList.size(); i++) { // for each command in the list
				filter = finalList.get(i);
				
//				if(filter instanceof grep) {
//					if(filter.input == null) {
//						System.out.print(Message.REQUIRES_INPUT.with_parameter("grep " +filter.word)));
//					}
//				}else {
				if(!error) {
					if(filter instanceof grep && (filter.input==(null) || filter.input.isEmpty()) ) {
						grep temp = (grep) filter;
						System.out.print(Message.REQUIRES_INPUT.with_parameter("grep " + temp.getWord()));
						
					}else if (filter instanceof WC && filter.input.isEmpty() && finalList.size()<3){
							System.out.print(Message.REQUIRES_INPUT.with_parameter("wc"));
							
					}else if (filter instanceof Cat && (!filter.input.isEmpty())) { 
						Cat temp = (Cat) filter;
						System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cat " + temp.getName())); 
					}else if (filter instanceof Cd && !filter.input.isEmpty()) {
						Cd temp = (Cd) filter; 
						System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cd "+ temp.getName()));
					}else if (filter instanceof LS && (!(filter.input == null) && !filter.input.isEmpty())) {
						System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("ls"));
					
					}else if( filter instanceof Pwd && (!(filter.input == null) && !filter.input.isEmpty())){
						System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("pwd"));
					}else {	
							filter.process();// process that command (each process method is different for each command)
							
							if(filter instanceof Cd && finalList.size()>2) {
								Cd temp = (Cd) filter; 
								System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter("cd " + temp.getName()));
								break;
							}
					}
//				if(filter.output.isEmpty() && !(filter instanceof Cd)) {
//					break;
//				}
				}
			}
			}
			System.out.print(newCommand);
			input = reader.nextLine();
		} 
		System.out.print(Message.GOODBYE);
		reader.close();
	}

}
