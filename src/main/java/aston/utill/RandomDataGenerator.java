package aston.utill;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;

import java.util.Random;

//Генерация случайных данных.
public class RandomDataGenerator {

    public static Bus[] generateRandomBuses(int size) {
        Random random = new Random();
        Bus[] buses = new Bus[size];
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(1000) + 1; // Генерация случайного номера
            String model = "Модель" + (random.nextInt(100) + 1); // Генерация случайной модели
            int mileage = random.nextInt(100000); // Генерация случайного пробега
            if (DataValidator.validateBusData(number, model, mileage)) {
                buses[i] = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();
            } else {
                i--; // Повторите генерацию, если данные некорректны
            }
        }
        return buses;
    }

    public static Student[] generateRandomStudents(int size) {
        Random random = new Random();
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            int groupNumber = random.nextInt(100) + 1;
            double averageGrade = random.nextDouble() * 10; // Оценка от 0 до 10
            int numberOfRecordBook = random.nextInt(1000) + 1;
            if (DataValidator.validateStudentData(groupNumber, averageGrade, numberOfRecordBook)) {
                students[i] = new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageGrade(averageGrade)
                        .setNumberOfRecordBook(numberOfRecordBook)
                        .build();
            } else {
                i--; // Повторите генерацию, если данные некорректны
            }
        }
        return students;
    }

    public static User[] generateRandomUsers(int size) {
        Random random = new Random();
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            String name = "Имя" + (random.nextInt(100) + 1);
            int password = random.nextInt(1000) + 1;
            String email = "email" + (random.nextInt(100) + 1) + "@example.com";
            if (DataValidator.validateUserData(name, password, email)) {
                users[i] = new User.Builder()
                        .setName(name)
                        .setPassword(password)
                        .setEmail(email)
                        .build();
            } else {
                i--; // Повторите генерацию, если данные некорректны
            }
        }
        return users;
    }
}
