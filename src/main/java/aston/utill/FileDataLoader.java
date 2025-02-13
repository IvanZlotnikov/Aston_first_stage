package aston.utill;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileDataLoader {

    public static Bus[] loadBusesFromFile(String filePath, int size) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < size) {
                count++;
            }
            Bus[] buses = new Bus[count];
            int index = 0;
            // Перезапускаем чтение файла для заполнения массива
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            while ((line = br2.readLine()) != null && index < size) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    int number = Integer.parseInt(data[0].trim());
                    String model = data[1].trim();
                    int mileage = Integer.parseInt(data[2].trim());
                    if (validateBusData(number, model, mileage)) {
                        buses[index++] = new Bus.Builder()
                                .setNumber(number)
                                .setModel(model)
                                .setMileage(mileage)
                                .build();
                    }
                }
            }
            return buses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Bus[0]; // Возвращаем пустой массив в случае ошибки
    }

    public static Student[] loadStudentsFromFile(String filePath, int size) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            // Сначала посчитаем количество строк в файле для создания массива нужного размера
            while ((line = br.readLine()) != null && count < size) {
                count++;
            }
            Student[] students = new Student[count];
            int index = 0;
            // Перезапускаем чтение файла для заполнения массива
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            while ((line = br2.readLine()) != null && index < size) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    int groupNumber = Integer.parseInt(data[0].trim());
                    double averageGrade = Double.parseDouble(data[1].trim());
                    int numberOfRecordBook = Integer.parseInt(data[2].trim());
                    if (validateStudentData(groupNumber, averageGrade, numberOfRecordBook)) {
                        students[index++] = new Student.Builder()
                                .setGroupNumber(groupNumber)
                                .setAverageGrade(averageGrade)
                                .setNumberOfRecordBook(numberOfRecordBook)
                                .build();
                    }
                }
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student[0]; // Возвращаем пустой массив в случае ошибки
    }

    public static User[] loadUsersFromFile(String filePath, int size) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            // Сначала посчитаем количество строк в файле для создания массива нужного размера
            while ((line = br.readLine()) != null && count < size) {
                count++;
            }
            User[] users = new User[count];
            int index = 0;
            // Перезапускаем чтение файла для заполнения массива
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            while ((line = br2.readLine()) != null && index < size) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0].trim();
                    int password = Integer.parseInt(data[1].trim());
                    String email = data[2].trim();
                    if (validateUserData(name, password, email)) {
                        users[index++] = new User.Builder()
                                .setName(name)
                                .setPassword(password)
                                .setEmail(email)
                                .build();
                    }
                }
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new User[0]; // Возвращаем пустой массив в случае ошибки
    }

    private static boolean validateBusData(int number, String model, int mileage) {
        return number > 0 && model != null && !model.isEmpty() && mileage >= 0;
    }

    private static boolean validateStudentData(int groupNumber, double averageGrade, int numberOfRecordBook) {
        return groupNumber > 0 && averageGrade >= 0 && numberOfRecordBook > 0;
    }

    private static boolean validateUserData(String name, int password, String email) {
        return name != null && !name.isEmpty() && password >= 0 && email != null && !email.isEmpty();
    }

}
