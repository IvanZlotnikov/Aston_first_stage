package aston.core;

import aston.strategy.DataFillerStrategy;
import aston.data.BusDataFiller;
import aston.data.StudentDataFiller;
import aston.data.UserDataFiller;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;
import aston.utils.SearchAndSort;
import aston.utils.DataValidator;
import aston.utils.FileWriterUtill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    private static int foundDataIndex = 0;

    public static void main(String[] args) {
        DataFillerStrategy<Bus> busFiller = new BusDataFiller("buses.txt");
        DataFillerStrategy<User> userFiller = new UserDataFiller("users.txt");
        DataFillerStrategy<Student> studentFiller = new StudentDataFiller("students.txt");

        Bus[] buses = busFiller.fillData();
        User[] users = userFiller.fillData();
        Student[] students = studentFiller.fillData();

        String[] foundData = new String[100];

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            DataValidator validator = new DataValidator();
            FileWriterUtill fileWriter = new FileWriterUtill();

            //clear all files
            fileWriter.clearFile("output_buses.txt");
            fileWriter.clearFile("output_users.txt");
            fileWriter.clearFile("output_students.txt");
            //скорее всего его чистить не нужно
//            fileWriter.clearFile("found_data.txt");

            Map<Integer, Consumer<BufferedReader>> actions = new HashMap<>();
            actions.put(1, br -> {
                try {
                    System.out.println("Введите пробег для поиска: ");
                    int mileage = Integer.parseInt(br.readLine());
                    Bus busTarget = new Bus.Builder().setMileage(mileage).build();
                    SearchAndSort<Bus> busSearch = new SearchAndSort<>(buses, busTarget,foundData,foundDataIndex);
                    busSearch.execute();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            });
            actions.put(2, br -> {
                try {
                    System.out.println("Введите имя для поиска: ");
                    String name = br.readLine();
                    User userTarget = new User.Builder().setName(name).build();
                    SearchAndSort<User> userSearch = new SearchAndSort<>(users, userTarget,foundData,foundDataIndex);
                    userSearch.execute();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            });
            actions.put(3, br -> {
                try {
                    System.out.println("Введите средний бал для поиска: ");
                    double averageGrade = Double.parseDouble(br.readLine());
                    Student studentTarget = new Student.Builder().setAverageGrade(averageGrade).build();
                    SearchAndSort<Student> studentSearch = new SearchAndSort<>(students, studentTarget,foundData,foundDataIndex);
                    studentSearch.execute();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            });
            actions.put(4, br -> {
                for (Bus bus : buses) {
                    if (validator.validate(bus.toString())) {
                        fileWriter.writeToFile(bus.toString(), "output_buses.txt", true);
                    }
                }
                for (User user : users) {
                    if (validator.validate(user.toString())) {
                        fileWriter.writeToFile(user.toString(), "output_users.txt", true);
                    }
                }
                for (Student student : students) {
                    if (validator.validate(student.toString())) {
                        fileWriter.writeToFile(student.toString(), "output_students.txt", true);
                    }
                }
                for (int i = 0; i < foundDataIndex; i++) {
                    fileWriter.writeToFile(foundData[i], "found_data.txt", true);
                }
                System.out.println("Данные записанны в файл.");
            });

            while (!exit) {
                System.out.println("Выберите действие: ");
                System.out.println("1. Сортировка и поиск автобусов");
                System.out.println("2. Сортировка и поиск пользователей");
                System.out.println("3. Сортировка и поиск студентов");
                System.out.println("4. Записать данные в файл");
                System.out.println("5. Выйти");

                try {
                    int choice = Integer.parseInt(bufferedReader.readLine());
                    Consumer<BufferedReader> action = actions.get(choice);
                    if (action != null)
                        action.accept(bufferedReader);
                    else if (choice == 5)
                        exit = true;
                    else
                        System.out.println("Неверный выбор попробуйте снова.");
                } catch (IOException e) {
                    System.out.println("Ошибка ввода : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка с BufferedReader: " + e.getMessage());
        }
    }

    public static int getFoundDataIndex() {
        return foundDataIndex;
    }

    public static void incrementFoundDataIndex(){
        foundDataIndex++;
    }
}
