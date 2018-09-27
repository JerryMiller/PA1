package cs131.pa1.filter.sequential;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
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
			for(int i = 0; i<finalList.size(); i++) { // for each command in the list
				filter = finalList.get(i); 
				filter.process(); // process that command (each process method is different for each command)
			}
			System.out.print(newCommand);
			input = reader.nextLine();
		} 
		System.out.print(Message.GOODBYE);
		reader.close();
	}

}
