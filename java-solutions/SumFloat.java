public class SumFloat {
    public static void main(String[] args) {
	    float sum = 0;
		int num0 = 0;
		boolean switch1 = false;
		boolean switch2 = false;
	    for (int i = 0; i < args.length; i++) {
			for (int num = 0; num <= args[i].length()-1; num ++) {
				char symbol = args[i].charAt(num);
			    boolean space = Character.isWhitespace(symbol);
                switch1 = !space;
			    if (switch1 && !switch2) {
				    num0 = num;
				    switch2 = true;
			    }
		        if (switch2 && !switch1) { 
			        String line = (args[i].substring(num0, num));
				    float add = Float.parseFloat(line);
	                sum = sum + add;
				    switch2 = false;
			    }
                if (switch2 && switch1) {
                    if (num == args[i].length()-1) {	
				        String line = (args[i].substring(num0));
				        float add = Float.parseFloat(line);
	                    sum = sum + add;
					    switch1 = false;
					    switch2 = false;
					}
				}
			}
        }
	    System.out.println(sum);
    }		
}