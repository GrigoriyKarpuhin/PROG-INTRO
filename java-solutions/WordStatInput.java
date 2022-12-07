import java.io.*;
import java.util.*;
//
public class WordStatInput {
	public static void main(String[] args) throws IOException {
		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		StringBuilder letters = new StringBuilder();
		Reader reader = new BufferedReader(new InputStreamReader(
			new FileInputStream(args[0]),
			"utf8"
		));
		try {
			int read = reader.read();
			while (read >= 0) {
				if (Character.isLetter(read) == true || read == '\'' || Character.DASH_PUNCTUATION == Character.getType(read)) {
					   letters.append((char)read);	
				} else {
					String lettersSt = letters.toString();
					if (words.containsKey(lettersSt.toLowerCase())) {
					words.replace(lettersSt.toLowerCase(), words.get(lettersSt.toLowerCase()) + 1);
					letters.setLength(0);	
					} else {
						if (letters.length() != 0) {
							words.put(lettersSt.toLowerCase(), 1);
							letters.setLength(0);
						}
					}
				}
				read = reader.read();
			}
		} finally {
			reader.close();
		}
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(args[1]),
			"utf8"
		));
		Set<String> key = words.keySet();
		try {
			for(String s: key) {
				writer.write(s + " " + words.get(s));
				writer.newLine();
			}
		} finally {
			writer.close();
		}
	}
}