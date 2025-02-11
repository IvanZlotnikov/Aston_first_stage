package aston.data;

import aston.model.Student;
import aston.utils.FileReaderUtill;
import aston.strategy.DataFillerStrategy;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class StudentDataFiller implements DataFillerStrategy<Student> {
    private final String filePath;

    public StudentDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Student[] fillDataFromFile() {
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

    @Override
    public Student[] fillDataRandomly(int count) {
        Random random = new Random();
        Student[] students = new Student[count];
        for (int i = 0; i < count; i++) {
            int groupNumber = random.nextInt(100) + 1;
            double averageGrade = 1.0 + (random.nextDouble() * 9.0);
            averageGrade = Math.round(averageGrade * 10.0) / 10.0;
            int recordBookNumber = random.nextInt(1000) + 1000;
            students[i] = new Student.Builder()
                    .setGroupNumber(groupNumber)
                    .setAverageGrade(averageGrade)
                    .setRecordBookNumber(recordBookNumber)
                    .build();
        }
        return students;
    }

    @Override
    public Student[] fillDataManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of students: ");
        int count = 0;
        try {
            count = scanner.nextInt();
        }
        catch (
                InputMismatchException e){
            System.out.println("вы ввели не цифру!!");
            return fillDataManually();}
        Student[] students = new Student[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Enter group number of student: ");
            int groupNumber = scanner.nextInt();
            System.out.println("Enter average grade of student: ");
            double averageGrade = scanner.nextDouble();
            System.out.println("Enter record book number of student: ");
            int recordBookNumber = scanner.nextInt();
            students[i] = new Student.Builder()
                    .setGroupNumber(groupNumber)
                    .setAverageGrade(averageGrade)
                    .setRecordBookNumber(recordBookNumber)
                    .build();
        }
        return students;
    }
}
