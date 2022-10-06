import java.io.*;
import java.util.*;
//
public class WordStatInput {
	public static void main(String[] args) throws IOException {
		LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
		String letters = ("");
		try {
			Reader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0]),
				"utf8"
			));
			try {
				int read = reader.read();
				while (read >= 0) {
					if (Character.isLetter(read) == true || read == '\'' || Character.DASH_PUNCTUATION == Character.getType(read)) {
					    letters += ((char)read);					
					} else {
						if (words.containsKey(letters.toLowerCase()) == true) {
						words.put(letters.toLowerCase(), words.get(letters.toLowerCase()) + 1);
						letters = ("");	
						} else {
							if (letters != "") {
								words.put(letters.toLowerCase(), 1);
								letters = ("");
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
		} catch (IOException e) {
		}
	}
}