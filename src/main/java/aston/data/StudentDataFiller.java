package aston.data;

import aston.data.AbstractDataFiller;
import aston.model.Student;

import java.lang.reflect.Array;

public class StudentDataFiller extends AbstractDataFiller<Student> {
    public StudentDataFiller() {
        super(Student.class);
    }

    @Override
    public Student createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных. Введите: номер группы, средний балл, номер зачетки.");
        }

        try {
            int groupNumber = Integer.parseInt(parts[0].trim());
            double averageGrade = Double.parseDouble(parts[1].trim());
            int recordBookNumber = Integer.parseInt(parts[2].trim());

            return new Student.Builder()
                    .setGroupNumber(groupNumber)
                    .setAverageGrade(averageGrade)
                    .setRecordBookNumber(recordBookNumber)
                    .build();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка парсинга чисел. Убедитесь, что номер группы, балл и зачетка - это числа.");
        }
    }

    @Override
    public Student[] generateRandomData(int size) {
        Student[] students = (Student[]) Array.newInstance(Student.class, size);
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
    public Student[] fillDataFromFile(int size) {
        System.out.println("Чтение студентов из файла пока не реализовано.");
        return new Student[0];
    }
}
