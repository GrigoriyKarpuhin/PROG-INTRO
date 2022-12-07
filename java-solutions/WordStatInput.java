import java.io.*;
import java.util.*;


//
public class WordStatInput {
	public static void main(String[] args) {
		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		OwnScanner scan1 = new OwnScanner(args[0], "utf8");
		while (scan1.hasNextLine()) {
			String line = scan1.nextLine();
			OwnScanner scan2 = new OwnScanner(line);
			while (scan2.hasNextWord()) {
				String lettersSt = scan2.nextWord();
				if (words.containsKey(lettersSt.toLowerCase())) {
					words.replace(lettersSt.toLowerCase(), words.get(lettersSt.toLowerCase()) + 1);
				} else {
					words.put(lettersSt.toLowerCase(), 1);
				}
			}
			scan2.close();
		}
		scan1.close();
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(args[1]),
					"utf8"
			));
			Set<String> key = words.keySet();
				for (String s : key) {
					writer.write(s + " " + words.get(s));
					writer.newLine();
				}
				writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}