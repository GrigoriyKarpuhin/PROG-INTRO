import java.io.*;

public class OwnScanner {

    private final Reader reader;
    private String stringcheck;
    private int intcheck;
    private String wordcheck;
    private boolean switchInt;
    private boolean switchWord;
    private boolean switchSpace;
    private boolean hasInt;
    private boolean hasWord;
    private boolean hasLetters;
    private final char[] buffer = new char[512];
    private int bufferSize;
    private int number = 0;
    private boolean hasNumber;
    private String numbercheck;
    private boolean switchNumber;

    public OwnScanner(String line)  {
        this.reader = new StringReader(line);
        BufferCreate();
    }

    public OwnScanner(InputStream stream) {
        this.reader = new InputStreamReader((stream));
        BufferCreate();
    }

    public OwnScanner(String file, String code) {
        try {
            this.reader = new InputStreamReader(new FileInputStream(file), code);
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferCreate();
    }

    public void BufferCreate() {
        try {
            bufferSize = reader.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        number = 0;
    }
    public String nextLine() {
        return stringcheck;
    }

    public boolean hasNextLine() {
        if (bufferSize <= 0) {
            return false;
        } else {
			int read = buffer[number];
			number++;
			if (number == bufferSize) {
                BufferCreate();
            }
			StringBuilder sbStr = new StringBuilder();
            while (number < bufferSize) {
                if (System.lineSeparator().length() == 1 && (read == '\r' || read == '\n')) {
                    break;
                }
                if (System.lineSeparator().length() == 2 && read == '\n') {
                    break;
                }
                sbStr.append((char) read);
                read = buffer[number];
                number++;
                if (number == bufferSize) {
                    BufferCreate();
                }
            }
            stringcheck = sbStr.toString();
            return true;
        }
    }

    public int nextInt() {
        if (hasInt && !switchInt) {
            switchInt = true;
            return intcheck;
        } else {
            StringBuilder sbInt = new StringBuilder();
            int read = buffer[number];
            number++;
            if (number == bufferSize) {
                BufferCreate();
            }
            while (number < bufferSize) {
                if (Character.isDigit((char) read) || (char)read == '-') {
                    switchSpace = true;
                    sbInt.append((char) read);
                } else {
                    if (switchSpace) {
                        switchSpace = false;
                        switchInt = false;
                        return Integer.parseInt(sbInt.toString());
                    }
                }
                read = buffer[number];
                number++;
                if (number == bufferSize) {
                    BufferCreate();
                }
            }
            switchInt = false;
            return Integer.parseInt(sbInt.toString());
        }
    }

    public boolean hasNextInt() {
        try {
            intcheck = nextInt();
            return hasInt = true;
        } catch (NumberFormatException nfe) {
            return hasInt = false;
        }
    }

    public String nextWord() {
        if (hasWord && !switchWord) {
            switchWord = true;
            return wordcheck;
        } else {
            StringBuilder letters = new StringBuilder();
            String word;
            int read = buffer[number];
            number++;
			if (number == bufferSize) {
                BufferCreate();
            }
            while (number < bufferSize) {
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
                read = buffer[number];
                number++;
                if (number == bufferSize) {
                    BufferCreate();
                }
            }
            switchWord = false;
            return letters.toString();
        }
    }

    public boolean hasNextWord()  {
        wordcheck = nextWord();
        if (wordcheck.length() != 0) {
            return hasWord = true;
        } else {
            return hasWord = false;
        }
    }
    public String nextNumber() {
        if (hasNumber && !switchNumber) {
            switchNumber = true;
            return numbercheck;
        } else {
            StringBuilder sb = new StringBuilder();
            int read = buffer[number];
            number++;
            if (number == bufferSize) {
                BufferCreate();
            }
            while (number < bufferSize) {
                if (Character.isDigit((char) read) || (char)read == '-') {
                    switchSpace = true;
                    sb.append((char) read);
                } else {
                    if (switchSpace) {
                        switchSpace = false;
                        switchNumber = false;
                        return sb.toString();
                    }
                }
                read = buffer[number];
                number++;
                if (number == bufferSize) {
                    BufferCreate();
                }
            }
            switchNumber = false;
            return String.valueOf(sb);
        }
    }

    public boolean hasNextNumber() {
            numbercheck = nextNumber();
            if (numbercheck.length() > 0) {
                return hasNumber = true;
            } else {
            return hasNumber = false;
        }
    }
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



