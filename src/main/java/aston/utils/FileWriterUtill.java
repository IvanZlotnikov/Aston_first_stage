package aston.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtill {

    public void writeToFile(String data, String filePath, boolean append) {
        if (data == null || filePath == null || data.isEmpty() || filePath.isEmpty())
            throw new IllegalArgumentException("data or path is null or empty");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clearFile(String filePath) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,false))){
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
