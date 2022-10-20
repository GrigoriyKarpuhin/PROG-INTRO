import java.io.*;

public class OwnScanner {

    private final Reader reader;
    private String stringcheck;
    private int intcheck;
    private String wordcheck;
    private boolean switchInt;
    private boolean switchWord;
    private boolean switchIntSpace;
    private boolean hasInt;
    private boolean hasWord;
    private boolean hasLetters;
    private final char[] buffer = new char[1024]; // read = reader.read(buffer, 0, buffer.length);
    private int bufferSize;

    public OwnScanner(String line) {
        this.reader = new StringReader(line);
    }

    public OwnScanner(InputStream stream) {
        this.reader = new InputStreamReader((stream));
    }

    public OwnScanner(String file, String code) throws FileNotFoundException, UnsupportedEncodingException {
            this.reader = new InputStreamReader(new FileInputStream(file), code);
    }

    public char[] BufferCreate() throws IOException {
        bufferSize = reader.read(buffer);
        return buffer;
    }
    public String nextLine() throws IOException {
        if (stringcheck.length() != 0) {
            return stringcheck;
        } else {
            StringBuilder sbStr = new StringBuilder();
            int read = reader.read();
            while (read >= 0) {
                if (read == '\n') {
                    break;
                } else {
                    sbStr.append((char) read);
                    read = reader.read();
                }
            }
            return sbStr.toString();
        }
    }

    public boolean hasNextLine() throws IOException {
        int read = reader.read();
        if (read < 0) {
            return false;
        } else {
            StringBuilder sbStr = new StringBuilder();
            while (read >= 0) {
                if (read == '\n') {
                    break;
                } else {
                    sbStr.append((char) read);
                    read = reader.read();
                }
            }
            stringcheck = sbStr.toString();
            return true;
        }
    }

    public int nextInt() throws IOException {
        if (hasInt && !switchInt) {
            switchInt = true;
            return intcheck;
        } else {
            StringBuilder sbInt = new StringBuilder();
            int read = reader.read();
            while (read >= 0) {
                if (Character.isDigit((char) read) || (char)read == '-') {
                    switchIntSpace = true;
                    sbInt.append((char) read);
                } else {
                    if (switchIntSpace) {
                        switchIntSpace = false;
                        switchInt = false;
                        return Integer.parseInt(sbInt.toString());
                    }
                }
                read = reader.read();
            }
            switchInt = false;
            return Integer.parseInt(sbInt.toString());
        }
    }

    public boolean hasNextInt() throws IOException {
        try {
            intcheck = nextInt();
            return hasInt = true;
        } catch (NumberFormatException nfe) {
            return hasInt = false;
        }
    }

    public String nextWord() throws IOException{
        if (hasWord && !switchWord) {
            switchWord = true;
            return wordcheck;
        } else {
            StringBuilder letters = new StringBuilder();
            String word;
            int read = reader.read();
            while (read >= 0) {
                if (Character.isLetter(read) || read == '\'' || Character.DASH_PUNCTUATION == Character.getType(read)) {
                    hasLetters = true;
                    letters.append((char) read);
                } else {
                    if (hasLetters) {
                        word = letters.toString();
                        letters.setLength(0);
                        switchWord = false;
                        hasLetters = false;
                        return word;
                    }
                }
                read = reader.read();
            }
            switchWord = false;
            return letters.toString();
        }
    }
    
    public boolean hasNextWord() throws IOException {
        wordcheck = nextWord();
        if (wordcheck.length() != 0) {
            return hasWord = true;
        } else {
            return hasWord = false;
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}



