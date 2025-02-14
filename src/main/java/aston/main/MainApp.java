package aston.main;

import aston.handler.MenuHandler;
import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;
import aston.handler.*;
import aston.input.ManualDataInput;
import aston.input.RandomDataInput;

import java.util.Scanner;

// главное событие...
public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String BUSES_FILE_PATH = "buses.txt";
    private static final String USERS_FILE_PATH = "users.txt";
    private static final String STUDENTS_FILE_PATH = "students.txt";
    private static final String OUTPUT_FILE_PATH = "output.txt";
    private static final String FOUND_ELEMENT_FILE_PATH = "found_element.txt";

    public static void main(String[] args) {
        while (true) {
            int choice = MenuHandler.showMainMenu();
            if (choice == 4) {
                System.exit(0);
            }
            handleMainChoice(choice);
        }
    }

    private static void handleMainChoice(int choice) {
        int typeChoice = MenuHandler.showDataTypeMenu();
        if (typeChoice == 4) {
            System.exit(0);
        }
        if (typeChoice >= 1 && typeChoice <= 4) {
            int size = MenuHandler.getSizeFromUser();
            Bus[] buses = null;
            Student[] students = null;
            User[] users = null;
            InputManager<Bus> busInputManager = new InputManager<>();
            InputManager<Student> studentInputManager = new InputManager<>();
            InputManager<User> userInputManager = new InputManager<>();

            switch (choice) {
                case 1:
                    DataManager.handleFileChoice(typeChoice, size, BUSES_FILE_PATH, STUDENTS_FILE_PATH, USERS_FILE_PATH);
                    break;
                case 2:
                    switch (typeChoice) {
                        case 1:
                            buses = busInputManager.inputData(new RandomDataInput<>(Bus.class), size);
                            DataManager.setBuses(buses);
                            break;
                        case 2:
                            students = studentInputManager.inputData(new RandomDataInput<>(Student.class), size);
                            DataManager.setStudents(students);
                            break;
                        case 3:
                            users = userInputManager.inputData(new RandomDataInput<>(User.class), size);
                            DataManager.setUsers(users);
                            break;
                        default:
                            System.err.println("Неверный выбор.");
                            break;
                    }
                    break;
                case 3:
                    switch (typeChoice) {
                        case 1:
                            buses = busInputManager.inputData(new ManualDataInput<>(Bus.class), size);
                            DataManager.setBuses(buses);
                            break;
                        case 2:
                            students = studentInputManager.inputData(new ManualDataInput<>(Student.class), size);
                            DataManager.setStudents(students);
                            break;
                        case 3:
                            users = userInputManager.inputData(new ManualDataInput<>(User.class), size);
                            DataManager.setUsers(users);
                            break;
                        default:
                            System.err.println("Неверный выбор.");
                            break;
                    }
                    break;
                default:
                    System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                    return;
            }

            buses = DataManager.getBuses();
            students = DataManager.getStudents();
            users = DataManager.getUsers();
            DisplayHandler.displayList(typeChoice, buses, students, users);
            chooseSortMethod(typeChoice);
            DisplayHandler.displaySortedElements(typeChoice, buses, students, users);
            performSearch(typeChoice);
            writeToFile();
        } else {
            System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
        }
    }

    private static void chooseSortMethod(int typeChoice) {
        while (true) {
            System.out.println("Выберите метод сортировки:");
            System.out.println("1. Selection Sort");
            System.out.println("2. Custom Sort");
            int sortChoice = scanner.nextInt();
            scanner.nextLine();

            if (sortChoice >= 1 && sortChoice <= 2) {
                System.out.println("Выберите поле для сортировки:");
                if (typeChoice == 1) {
                    System.out.println("1. Номер");
                    System.out.println("2. Модель");
                    System.out.println("3. Пробег");
                } else if (typeChoice == 2) {
                    System.out.println("1. Номер группы");
                    System.out.println("2. Средний бал");
                    System.out.println("3. Номер зачетной книжки");
                }
                if (typeChoice == 3) {
                    System.out.println("1. Имя");
                    System.out.println("2. Пароль");
                    System.out.println("3. Почта");
                }
                int fieldChoice = scanner.nextInt();
                scanner.nextLine();

                Bus[] buses = DataManager.getBuses();
                Student[] students = DataManager.getStudents();
                User[] users = DataManager.getUsers();
                SortAndSearchHandler.sort(typeChoice, sortChoice, fieldChoice, buses, students, users);
                break;
            } else {
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void performSearch(int typeChoice) {
        switch (typeChoice) {
            case 1:
                Bus[] buses = DataManager.getBuses();
                if (buses != null) {
                    System.out.println("Введите номер автобуса для поиска:");
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    Bus key = new Bus.Builder()
                            .setNumber(number)
                            .build();
                    int index = SortAndSearchHandler.search(typeChoice, buses, null, null, key);
                    if (index >= 0) {
                        System.out.println("Автобус найден на позиции: " + index);
                        writeFoundElementToFile(buses[index]);
                    } else {
                        System.err.println("Автобус не найден.");
                    }
                }
                break;
            case 2:
                Student[] students = DataManager.getStudents();
                if (students != null) {
                    System.out.println("Введите номер группы студента для поиска:");
                    int groupNumber = scanner.nextInt();
                    scanner.nextLine();
                    Student key = new Student.Builder()
                            .setGroupNumber(groupNumber)
                            .build();
                    int index = SortAndSearchHandler.search(typeChoice, null, students, null, key);
                    if (index >= 0) {
                        System.out.println("Студент найден на позиции: " + index);
                        writeFoundElementToFile(students[index]);
                    } else {
                        System.err.println("Студент не найден.");
                    }
                }
                break;
            case 3:
                User[] users = DataManager.getUsers();
                if (users != null) {
                    System.out.println("Введите пароль пользователя для поиска:");
                    int password = scanner.nextInt();
                    scanner.nextLine();
                    User key = new User.Builder()
                            .setPassword(password)
                            .build();
                    int index = SortAndSearchHandler.search(typeChoice, null, null, users, key);
                    if (index >= 0) {
                        System.out.println("Пользователь найден на позиции: " + index);
                        writeFoundElementToFile(users[index]);
                    } else {
                        System.err.println("Пользователь не найден.");
                    }
                }
                break;
            default:
                System.err.println("Неверный выбор.");
                break;
        }
    }

    private static void writeFoundElementToFile(Object element) {
        String data = FileManager.buildDataString(element);
        FileManager.writeToFile(data, FOUND_ELEMENT_FILE_PATH);
        System.out.println("Найденный элемент записан в файл: " + FOUND_ELEMENT_FILE_PATH);
    }

    private static void writeToFile() {
        StringBuilder data = new StringBuilder();

        Bus[] buses = DataManager.getBuses();
        if (buses != null) {
            for (Bus bus : buses) {
                data.append(FileManager.buildDataString(bus));
            }
        }

        Student[] students = DataManager.getStudents();
        if (students != null) {
            for (Student student : students) {
                data.append(FileManager.buildDataString(student));
            }
        }

        User[] users = DataManager.getUsers();
        if (users != null) {
            for (User user : users) {
                data.append(FileManager.buildDataString(user));
            }
        }

        FileManager.writeToFile(data.toString(), OUTPUT_FILE_PATH);
        System.out.println("Данные записаны в файл: " + OUTPUT_FILE_PATH);
    }
}


