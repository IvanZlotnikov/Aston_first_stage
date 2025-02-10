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
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private static String[] foundData = new String[100];
    private static int foundDataIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите способ заполнения данных:");
        System.out.println("1 - из файла: ");
        System.out.println("2 - случайные данные: ");
        System.out.println("3 - вручную: ");
        int choiceFillData = scanner.nextInt();

        DataFillerStrategy<Bus> busFiller = new BusDataFiller("buses.txt");
        DataFillerStrategy<User> userFiller = new UserDataFiller("users.txt");
        DataFillerStrategy<Student> studentFiller = new StudentDataFiller("students.txt");

        Bus[] buses;
        User[] users;
        Student[] students;

        switch (choiceFillData) {
            case 1:
                buses = busFiller.fillDataFromFile();
                users = userFiller.fillDataFromFile();
                students = studentFiller.fillDataFromFile();
                break;
            case 2:
                System.out.println("Ввелите количество автобусов: ");
                int busCount = scanner.nextInt();
                buses = busFiller.fillDataRandomly(busCount);

                System.out.println("Введите количество пользователей: ");
                int userCount = scanner.nextInt();
                users = userFiller.fillDataRandomly(userCount);

                System.out.println("Введите количество студентов: ");
                int studentCount = scanner.nextInt();
                students = studentFiller.fillDataRandomly(studentCount);
                break;
            case 3:
                buses = busFiller.fillDataManually();
                users = userFiller.fillDataManually();
                students = studentFiller.fillDataManually();
                break;
            default:
                System.out.println("Неверный выбор. Заполнение данными из файла.");
                buses = busFiller.fillDataFromFile();
                users = userFiller.fillDataFromFile();
                students = studentFiller.fillDataFromFile();
                break;
        }

        String[] foundData = new String[100];

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            DataValidator validator = new DataValidator();
            FileWriterUtill fileWriter = new FileWriterUtill();

            //clear all files
            fileWriter.clearFile("output_buses.txt");
            fileWriter.clearFile("output_users.txt");
            fileWriter.clearFile("output_students.txt");
            fileWriter.clearFile("found_data.txt");

            Map<Integer, Consumer<BufferedReader>> actions = new HashMap<>();
            actions.put(1, br -> {
                System.out.println("Список автобусов: ");
                for (Bus bus : buses) {
                    System.out.println(bus);
                }
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
                System.out.println("Список пользователей: ");
                for (User user : users) {
                    System.out.println(user);
                }
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
                System.out.println("Список студентов: ");
                for (Student student : students) {
                    System.out.println(student);
                }
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

                //очистка массива после записи чтобы повторно не записывало
                clearFoundData();
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

    public static void incrementFoundDataIndex() {
        foundDataIndex++;
    }
    private static void clearFoundData(){
        foundData = new String[100];
        foundDataIndex = 0;
    }
}
