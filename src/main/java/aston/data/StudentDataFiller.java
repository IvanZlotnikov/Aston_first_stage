package aston.data;

import aston.core.DataFiller;
import aston.model.Student;
import aston.utils.DataFillerExtended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class StudentDataFiller implements DataFillerExtended<Student> {
    private final Random random = new Random();

    @Override
    public Student[] fillData(int size, String method) {
        switch (method) {
            case "manual":
                return fillDataManually(size);
            case "random":
                return generateRandomData(size);
            case "file":
                System.out.println("Введите путь к файлу:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String filePath = reader.readLine();
                    return fillDataFromFile(filePath);
                } catch (IOException e) {
                    System.out.println("Ошибка чтения пути файла: " + e.getMessage());
                }
        }
        return new Student[0];
    }

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
            int groupNumber = random.nextInt(100) + 1;
            double averageGrade = 2.0 + (random.nextDouble() * 3.0);
            int recordBookNumber = random.nextInt(1000);
            students[i] = new Student.Builder()
                    .setGroupNumber(groupNumber)
                    .setAverageGrade(Math.round(averageGrade * 10.0) / 10.0)
                    .setRecordBookNumber(recordBookNumber)
                    .build();
        }
        return students;
    }

    @Override
    public Student[] fillDataManually(int size) {
        return DataFillerExtended.super.fillDataManually(size);
    }

    @Override
    public Student[] fillDataFromFile(String filePath) {
        return DataFillerExtended.super.fillDataFromFile(filePath);
    }

    @Override
    public Student[] fillData() {
        return new Student[0];
    }
}
