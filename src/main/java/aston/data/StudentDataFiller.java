package aston.data;

import aston.core.DataFiller;
import aston.model.Student;

public class StudentDataFiller implements DataFiller<Student> {
    @Override
    public Student[] fillData() {
        return new Student[]{
                new Student.Builder().setGroupNumber(11).setAverageGrade(5.5).setRecordBookNumber(2).build(),
                new Student.Builder().setGroupNumber(12).setAverageGrade(4.5).setRecordBookNumber(1).build(),
                new Student.Builder().setGroupNumber(13).setAverageGrade(6.5).setRecordBookNumber(3).build(),
        };
    }
}
