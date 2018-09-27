package cs131.pa1.filter.sequential;

public class Printer extends SequentialFilter {
	
	public void process(){
		while (!input.isEmpty()){
			String line = input.poll();
			if(this.prev instanceof Cd) {
				
			}else {
				String finalOutput = line;
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
		return null;
	}
}

