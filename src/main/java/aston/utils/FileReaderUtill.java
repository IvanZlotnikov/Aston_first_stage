package aston.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtill {
    public static String[] readFile(String filePath) {
        String[] lines = new String[100];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (index >= lines.length) {
                    String[] newLines = new String[lines.length * 2];
                    System.arraycopy(lines, 0, newLines, 0, lines.length);
                    lines = newLines;
                }
                lines[index] = line;
                index++;
            }
        } catch (IOException e) {
            e.getMessage();
        }
        String[] res = new String[index];
        System.arraycopy(lines, 0, res, 0, index);
        return res;
    }
}
