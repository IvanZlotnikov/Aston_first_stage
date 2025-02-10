package aston.data;

import aston.core.DataFiller;
import aston.model.Student;
import aston.utils.FileReaderUtill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDataFiller implements DataFiller<Student> {
    private String filePath;

    public StudentDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Student[] fillData() {
        String[] lines = FileReaderUtill.readFile(filePath);
        Student[] students = new Student[lines.length];
        Pattern pattern = Pattern.compile("(\\d+) (\\w+) (\\d+)");
        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                students[i] = new Student.Builder()
                        .setGroupNumber(Integer.parseInt(matcher.group(0)))
                        .setAverageGrade(Double.parseDouble(matcher.group(1)))
                        .setRecordBookNumber(Integer.parseInt(matcher.group(2)))
                        .build();
            }
        }
        return students;
    }
}
