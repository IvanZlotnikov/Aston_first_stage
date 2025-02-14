package aston.utill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
    public static void writeToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
