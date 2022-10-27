
import java.util.Arrays;
//
public class ReverseOct {
    public static void main(String[] args) {
	    String[][] arrInts = new String[1][1];
		int column = 0;
		OwnScanner scan1 = new OwnScanner(System.in);
		while (scan1.hasNextLine()) {
			if (column == arrInts.length) {
			    arrInts = Arrays.copyOf(arrInts, arrInts.length * 2);
			}	
			arrInts[column] = new String[1];
			int line = 0;
			String lines = scan1.nextLine();
			OwnScanner scan2 = new OwnScanner(lines);
            while (scan2.hasNextNumber()) {
				if (line == arrInts[column].length) {
					arrInts[column] = Arrays.copyOf(arrInts[column], arrInts[column].length * 2);
			    } 
				arrInts[column][line] = scan2.nextNumber();
				line += 1;		
		    }
			scan2.close();
			arrInts[column] = Arrays.copyOf(arrInts[column], line);
			column += 1;
		}
		scan1.close();
		arrInts = Arrays.copyOf(arrInts, column);
        for (int i = column - 1; i >= 0; i--) {
	        for (int j = arrInts[i].length - 1; j >= 0; j--) {
					System.out.print(arrInts[i][j]);
				    System.out.print(" ");
			}
			System.out.println("");
		}
    }
}       