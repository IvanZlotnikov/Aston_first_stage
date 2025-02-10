package aston.data;

import aston.model.Student;
import aston.utils.FileReaderUtill;
import aston.strategy.DataFillerStrategy;

public class StudentDataFiller implements DataFillerStrategy<Student> {
    private String filePath;

    public StudentDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Student[] fillData() {
        String[] lines = FileReaderUtill.readFile(filePath);
        Student[] students = new Student[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splits = lines[i].split(",");
            students[i] = new Student.Builder()
                    .setGroupNumber(Integer.parseInt(splits[0]))
                    .setAverageGrade(Double.parseDouble(splits[1]))
                    .setRecordBookNumber(Integer.parseInt(splits[2]))
                    .build();
        }
        return students;
    }
}
