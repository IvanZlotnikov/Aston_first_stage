package aston.data;

import aston.data.AbstractDataFiller;
import aston.model.Student;

public class StudentDataFiller extends AbstractDataFiller<Student> {
    @Override
    public Student createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных: " + data);
        }
        return new Student.Builder()
                .setGroupNumber(Integer.parseInt(parts[0].trim()))
                .setAverageGrade(Double.parseDouble(parts[1].trim()))
                .setRecordBookNumber(Integer.parseInt(parts[2].trim()))
                .build();
    }

    @Override
    public Student[] generateRandomData(int size) {
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            students[i] = new Student.Builder()
                    .setGroupNumber(random.nextInt(100) + 1)
                    .setAverageGrade(Math.round((2.0 + random.nextDouble() * 3.0) * 10.0) / 10.0)
                    .setRecordBookNumber(random.nextInt(1000))
                    .build();
        }
        return students;
    }

    @Override
    public Student[] fillDataFromFile( int size) {
        // Заглушка для чтения из файла
        System.out.println("Чтение студентов из файла пока не реализовано.");
        return new Student[0];
    }
}
