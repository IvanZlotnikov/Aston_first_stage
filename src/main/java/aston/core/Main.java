package aston.core;

import aston.data.BusDataFiller;
import aston.data.StudentDataFiller;
import aston.data.UserDataFiller;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;
import aston.strategy.SearchStrategy;
import aston.utils.DataValidator;
import aston.utils.FileWriterUtill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        DataFiller<Bus> busFiller = new BusDataFiller();
        DataFiller<User> userFiller = new UserDataFiller();
        DataFiller<Student> studentFiller = new StudentDataFiller();

        Bus[] buses = busFiller.fillData();
        User[] users = userFiller.fillData();
        Student[] students = studentFiller.fillData();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            DataValidator validator = new DataValidator();
            FileWriterUtill fileWriter = new FileWriterUtill();

            Map<Integer, Consumer<BufferedReader>> actions = new HashMap<>();
            actions.put(1, br -> {
                try {
                    System.out.println("Введите пробег для поиска: ");
                    int mileage = Integer.parseInt(br.readLine());
                    Bus busTarget = new Bus.Builder().setMileage(mileage).build();
                    SearchStrategy<Bus> busSearch = new SearchStrategy<>(buses, busTarget);
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
                    SearchStrategy<User> userSearch = new SearchStrategy<>(users, userTarget);
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
                    SearchStrategy<Student> studentSearch = new SearchStrategy<>(students, studentTarget);
                    studentSearch.execute();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода: " + e.getMessage());
                }
            });
            actions.put(4, br -> {
                for (Bus bus : buses) {
                    if (validator.validate(bus.toString())) {
                        fileWriter.writeToFile(bus.toString(), "output_buses.txt");
                    }
                }
                for (User user : users) {
                    if (validator.validate(user.toString())) {
                        fileWriter.writeToFile(user.toString(), "output_users.txt");
                    }
                }
                for (Student student : students) {
                    if (validator.validate(student.toString())) {
                        fileWriter.writeToFile(student.toString(), "output_students.txt");
                    }
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
}
