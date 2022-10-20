import java.io.*;
import java.util.*;
//
public class WsppPosition {
	public static void main(String[] args) throws IOException {
		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		LinkedHashMap<String, StringBuilder> wspp = new LinkedHashMap<>();
		StringBuilder letters = new StringBuilder();
		int count = 1;
		int countSt = 1;
		OwnScanner scan1 = new OwnScanner(args[0], "utf8");
		while (scan1.hasNextLine()) {
			String line = scan1.nextLine();
			OwnScanner scan2 = new OwnScanner(line);
			while (scan2.hasNextWord()) {
				String lettersSt = scan2.nextWord();
				if (words.containsKey(lettersSt.toLowerCase())) {
					words.replace(lettersSt.toLowerCase(), words.get(lettersSt.toLowerCase()) + 1);
					wspp.replace(lettersSt.toLowerCase(), wspp.get(lettersSt.toLowerCase()).append(" " + countSt + ":" + count));
					count++;
				} else {
					words.put(lettersSt.toLowerCase(), 1);
					wspp.put(lettersSt.toLowerCase(), new StringBuilder(countSt + ":" + count + ""));
					count++;
				}
			}
			count = 1;
			countSt++;
			scan2.close();
		}
		scan1.close();	
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(args[1]),
			"utf8"
		));
		Set<String> key = words.keySet();
		try {
			for(String s: key) {
				writer.write(s + " " + words.get(s) + " " + wspp.get(s));
				writer.newLine();
			}
		} finally {
			writer.close();
		}
	}
}