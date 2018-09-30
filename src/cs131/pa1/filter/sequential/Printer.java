package cs131.pa1.filter.sequential;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class Printer extends SequentialFilter {
	String fileName = null;
	public Printer(String file) {
		// TODO Auto-generated constructor stub
		this.fileName = file;
	}
	
	public Printer() {
	}
	
	public void process(){
		while (!input.isEmpty()){
			String line = input.poll();
			if(this.prev instanceof Cd) {
				
			}else if(fileName != null){
				processLine(line);
			}else {
				String finalOutput = line;
//				output.add(finalOutput);
				System.out.println(finalOutput);
			}
		
		}
	}

//	protected String processLine(String line) {
//		
//		if(this.prev instanceof Cd) {
//			
//		}else {
//			String finalOutput = input.poll();
//			System.out.print(finalOutput);
//		}
//				
//		
//		return null;
//	}
//	


	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if(!fileName.contains(".") || line == null) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
			input = new LinkedList<String>();
		}
		File file = new File(SequentialREPL.currentWorkingDirectory);
		File newFile = new File(file, fileName);
		if(!newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(newFile.getCanonicalPath(), true);
			bw = new BufferedWriter(fw);
			bw.write(line + "\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
}

