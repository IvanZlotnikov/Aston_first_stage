package aston.utill;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;

import java.util.Scanner;

//Ручной ввод данных через консоль.
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static Bus[] inputBusesManually(int size) {
        Bus[] buses = new Bus[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите данные для автобуса " + (i + 1));
            System.out.print("Номер: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // переход на новую строку

            System.out.print("Модель: ");
            String model = scanner.nextLine();

            System.out.print("Пробег: ");
            int mileage = scanner.nextInt();
            scanner.nextLine();// переход на новую строку

            if (DataValidator.validateBusData(number, model, mileage)) {
                buses[i] = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();
            } else {
                System.err.println("Некорректные данные. попробуй снова.");
                i--;
            }
        }
        return buses;
    }

    public static User[] inputUsersManually(int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите данные для пользователя " + (i + 1));
            System.out.print("Имя: ");
            String name = scanner.nextLine();

            System.out.print("Пароль(числовой): ");
            int password = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Почта: ");
            String email = scanner.nextLine();

            if (DataValidator.validateUserData(name, password, email)) {
                users[i] = new User.Builder()
                        .setName(name)
                        .setPassword(password)
                        .setEmail(email)
                        .build();
            } else {
                System.err.println("Некорректные данные, попробуйте снова.");
                i--; // повторите ввод для тек пользователя
            }
        }
        return users;
    }

    public static Student[] inputStudentsManually(int size) {
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите данные для студента " + (i + 1));
            System.out.print("Номер группы: ");
            int groupNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Средний балл: ");
            double averageGrade = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Номер зачетной книжки: ");
            int numberOfRecordBook = scanner.nextInt();
            scanner.nextLine();

            if (DataValidator.validateStudentData(groupNumber, averageGrade, numberOfRecordBook)) {
                students[i] = new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageGrade(averageGrade)
                        .setNumberOfRecordBook(numberOfRecordBook)
                        .build();
            } else {
                System.err.println("Некорректные данные, попробуйте снова.");
                i--; // повторите ввод для тек студента
            }
        }
        return students;
    }
}
