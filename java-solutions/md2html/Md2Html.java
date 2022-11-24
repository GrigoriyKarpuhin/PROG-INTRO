package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        try {
            int maxHeaderLevel = 0;
            int hasType = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
            ));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
            ));
            StringBuilder line = new StringBuilder(reader.readLine());
            StringBuilder part = new StringBuilder();
            while (line != null) {
                boolean paragraph = true;
                while (line.isEmpty()) {
                    line = new StringBuilder(reader.readLine());
                }
                String next = reader.readLine();
                if (next != null) {
                    paragraph = next.isEmpty();
                }
                char[] symbols = String.valueOf(line).toCharArray();
                int headerLevel = 0;
                if (symbols[0] == '#') {
                    headerLevel = 1;
                    while (symbols[headerLevel] == '#') {
                        headerLevel++;
                    }
                    if (symbols[headerLevel] != ' ') {
                        headerLevel = 0;
                    }
                }
                if (hasType == 0) {
                    if (headerLevel > 0) {
                        line = new StringBuilder(line.substring(headerLevel + 1, line.length()));
                        line.insert(0, "<h" + headerLevel + ">");
                        if (paragraph) {
                            line.append("</h").append(headerLevel).append(">");
                        } else {
                            hasType = 1;
                        }
                        maxHeaderLevel = headerLevel;
                    } else {
                        line.insert(0, "<p>");
                        if (paragraph) {
                            line.append("</p>");
                        } else {
                            hasType = 2;
                        }
                    }
                } else {
                    if (hasType == 1 && paragraph) {
                        line.append("</h").append(maxHeaderLevel).append(">");
                        hasType = 0;
                    } else {
                        if (paragraph) {
                            line.append("</p>");
                            hasType = 0;
                        }
                    }
                }
                part.append(line);
                if (paragraph) {
                    if (part.charAt(1) == 'h') {
                        part = converter(new StringBuilder(part.substring(4, part.length() - 5)));
                        part.insert(0, "<h" + maxHeaderLevel + ">");
                        part.append("</h").append(maxHeaderLevel).append(">");
                    } else {
                        part = converter(new StringBuilder(part.substring(3, part.length() - 4)));
                        part.insert(0, "<p>");
                        part.append("</p>");
                    }
                    writer.write(String.valueOf(part));
                    writer.newLine();
                    part = new StringBuilder();
                } else {
                    part.append(System.lineSeparator());
                }
                if (next != null) {
                    line = new StringBuilder(next);
                } else {
                    line = null;
                }
            }
            reader.close();
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static StringBuilder converter(StringBuilder request) {
        int hasEmphasis1 = -1;
        int hasStrong1 = -1;
        int hasEmphasis2 = -1;
        int hasStrong2 = -1;
        int hasStrikeout = -1;
        int hasCode = -1;
        int hasMark = -1;
        int skipDouble = -1;
        boolean nextStrong = false;
        String line = String.valueOf(request) + '.';
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length() - 1; i++) {
            boolean skipNext = false;
            if (line.charAt(i) == '\\') {
                builder.append(line.charAt(i + 1));
                i += 1;
                continue;
            }
            if (line.charAt(i) == '*') {
                if (line.charAt(i + 1) == '*') {
                    if (hasStrong1 == -1) {
                        hasStrong1 = builder.length();
                        nextStrong = true;
                    } else {
                        builder.delete(hasStrong1, hasStrong1 + 2);
                        builder.insert(hasStrong1, "<strong>");
                        builder.append("</strong>");
                        hasStrong1 = -1;
                        skipDouble = 1;
                    }
                } else {
                    if (nextStrong) {
                        nextStrong = false;
                    } else {
                        if (hasEmphasis1 == -1 && skipDouble == -1) {
                            hasEmphasis1 = builder.length();
                        } else {
                            if (skipDouble == -1) {
                                builder.delete(hasEmphasis1, hasEmphasis1 + 1);
                                builder.insert(hasEmphasis1, "<em>");
                                builder.append("</em>");
                                hasEmphasis1 = -1;
                                skipNext = true;
                            }
                        }
                    }
                }
            }
            if (line.charAt(i) == '_') {
                if (line.charAt(i + 1) == '_') {
                    if (hasStrong2 == -1) {
                        hasStrong2 = builder.length();
                        nextStrong = true;
                    } else {
                        builder.delete(hasStrong2, hasStrong2 + 2);
                        builder.insert(hasStrong2, "<strong>");
                        builder.append("</strong>");
                        hasStrong2 = -1;
                        skipDouble = 1;
                    }
                } else {
                    if (nextStrong) {
                        nextStrong = false;
                    } else {
                        if (hasEmphasis2 == -1 && skipDouble == -1) {
                            hasEmphasis2 = builder.length();
                        } else {
                            if (skipDouble == -1) {
                                builder.delete(hasEmphasis2, hasEmphasis2 + 1);
                                builder.insert(hasEmphasis2, "<em>");
                                builder.append("</em>");
                                hasEmphasis2 = -1;
                                skipNext = true;
                            }
                        }
                    }
                }
            }
            if (line.charAt(i) == '-' && line.charAt(i + 1) == '-') {
                if (hasStrikeout == -1) {
                    hasStrikeout = builder.length();
                } else {
                    builder.delete(hasStrikeout, hasStrikeout + 2);
                    builder.insert(hasStrikeout, "<s>");
                    builder.append("</s>");
                    hasStrikeout = -1;
                    skipDouble = 1;
                }
            }
            if (line.charAt(i) == '`') {
                if (hasCode == -1) {
                    hasCode = builder.length();
                } else {
                    builder.delete(hasCode, hasCode + 1);
                    builder.insert(hasCode, "<code>");
                    builder.append("</code>");
                    hasCode = -1;
                    skipNext = true;
                }
            }
            if (line.charAt(i) == '~') {
                if (hasMark == -1) {
                    hasMark = builder.length();
                } else {
                    builder.delete(hasMark, hasMark + 1);
                    builder.insert(hasMark, "<mark>");
                    builder.append("</mark>");
                    hasMark = -1;
                    skipNext = true;
                }
            }
            if (line.charAt(i) == '<') {
                builder.append("&lt;");
                skipNext = true;
            }
            if (line.charAt(i) == '>') {
                builder.append("&gt;");
                skipNext = true;
            }
            if (line.charAt(i) == '&') {
                builder.append("&amp;");
                skipNext = true;
            }
            if (!skipNext && skipDouble == -1) {
                builder.append(line.charAt(i));
            }
            if (skipDouble > -1) {
                skipDouble--;
            }
        }
        builder.append(line.charAt(line.length() - 1));
        return builder.delete(builder.length() - 1, builder.length());
    }
}
