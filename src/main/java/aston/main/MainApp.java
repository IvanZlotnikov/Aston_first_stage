package aston.main;
import aston.algoritms.BinarySearch;
import aston.algoritms.CustomSort;
import aston.algoritms.SelectionSort;
import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;
import aston.utill.FileDataLoader;
import aston.utill.FileWriterUtil;
import aston.utill.InputHandler;
import aston.utill.RandomDataGenerator;

import java.util.Scanner;


public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Bus[] buses;
    private static Student[] students;
    private static User[] users;

    private static final String BUSES_FILE_PATH = "buses.txt";
    private static final String USERS_FILE_PATH = "users.txt";
    private static final String STUDENTS_FILE_PATH = "students.txt";
    private static final String OUTPUT_FILE_PATH = "output.txt";
    private static final String FOUND_ELEMENT_FILE_PATH = "found_element.txt";

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("Выберите тип ввода данных:");
            System.out.println("1. Заполнение массива из файла");
            System.out.println("2. Генерация случайных данных");
            System.out.println("3. Ввод данных вручную");
            System.out.println("4. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 4) {
                handleMainChoice(choice);
                if (choice == 4) break;
            } else {
                System.err.println("Неверный выбор.Пожалуйста, попробуйте снова");
            }
        }
    }

    private static void handleMainChoice(int choice) {
        while (true) {
            showDataTypeMenu();
            int typeChoice = scanner.nextInt();
            scanner.nextLine();
            if (typeChoice >= 1 && typeChoice <= 3) {
                int size = getSizeFromUser();
                switch (choice) {
                    case 1:
                        handleFileChoice(typeChoice, size);
                        break;
                    case 2:
                        handleRandomChoice(typeChoice, size);
                        break;
                    case 3:
                        handleManualChoice(typeChoice, size);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                        continue;
                }
                displayList(typeChoice); //вывод на экран списка
                chooseSortMethod(typeChoice); // выбор сортировки
                displaySortedElements(typeChoice); // отсортированный список на экран
                performSearch(typeChoice);// поиск элемента
                writeToFile(); // запись в файл
                break;
            } else {
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void showDataTypeMenu() {
        System.out.println("Выберите тип данных для заполнения:");
        System.out.println("1. Автобусы");
        System.out.println("2. Студенты");
        System.out.println("3. Пользователи");
    }

    private static int getSizeFromUser() {
        System.out.println("Введите количество элементов:");
        return scanner.nextInt();
    }

    private static void handleFileChoice(int typeChoice, int size) {
        switch (typeChoice) {
            case 1:
                buses = FileDataLoader.loadBusesFromFile(BUSES_FILE_PATH, size);
                break;
            case 2:
                students = FileDataLoader.loadStudentsFromFile(STUDENTS_FILE_PATH, size);
                break;
            case 3:
                users = FileDataLoader.loadUsersFromFile(USERS_FILE_PATH, size);
                break;
            default:
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                break;
        }
    }

    private static void handleRandomChoice(int typeChoice, int size) {
        switch (typeChoice) {
            case 1:
                buses = RandomDataGenerator.generateRandomBuses(size);
                break;
            case 2:
                students = RandomDataGenerator.generateRandomStudents(size);
                break;
            case 3:
                users = RandomDataGenerator.generateRandomUsers(size);
                break;
            default:
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                break;
        }
    }

    private static void handleManualChoice(int typeChoice, int size) {
        switch (typeChoice) {
            case 1:
                buses = InputHandler.inputBusesManually(size);
                break;
            case 2:
                students = InputHandler.inputStudentsManually(size);
                break;
            case 3:
                users = InputHandler.inputUsersManually(size);
                break;
            default:
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                break;
        }
    }

    private static void displayList(int typeChoice) {
        switch (typeChoice) {
            case 1:
                System.out.println("Список автобусов:");
                if (buses != null) {
                    for (Bus bus : buses) {
                        System.out.println("Номер: " + bus.getNumber() +
                                           ", Модель: " + bus.getModel() +
                                           ", Пробеег: " + bus.getMileage());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            case 2:
                System.out.println("Список студентов:");
                if (students != null) {
                    for (Student student : students) {
                        System.out.println("Номер группы: " + student.getGroupNumber() +
                                           ", Средний балл: " + student.getAverageGrade() +
                                           ", Номер зачетной книжки: " + student.getNumberOfRecordBook());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            case 3:
                System.out.println("Список пользователей:");
                if (users != null) {
                    for (User user : users) {
                        System.out.println("Имя пользователя: " + user.getName() +
                                           ", Пароль: " + user.getPassword() +
                                           ", Почта: " + user.getEmail());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            default:
                System.err.println("Неверный выбор.");
                break;
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
                switch (typeChoice) {
                    case 1:
                        if (buses != null) {
                            if (sortChoice == 1) {
                                SelectionSort.sort(buses);
                            } else {
                                CustomSort.sort(buses, Bus::getNumber);
                            }
                        }
                        break;
                    case 2:
                        if (students != null) {
                            if (sortChoice == 1) {
                                SelectionSort.sort(students);
                            } else {
                                CustomSort.sort(students, Student::getGroupNumber);
                            }
                        }
                        break;
                    case 3:
                        if (users != null) {
                            if (sortChoice == 1) {
                                SelectionSort.sort(users);
                            } else {
                                CustomSort.sort(users, User::getPassword);
                            }
                        }
                        break;
                    default:
                        System.err.println("Неверный выбор.");
                        break;
                }
                break;
            } else {
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void displaySortedElements(int typeChoice) {
        switch (typeChoice) {
            case 1:
                System.out.println("Отсортированный список автобусов:");
                if (buses != null) {
                    for (Bus bus : buses) {
                        System.out.println("Номер: " + bus.getNumber() +
                                           ", Модель: " + bus.getModel() +
                                           ", Пробег: " + bus.getMileage());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            case 2:
                System.out.println("Отсортированный список студентов:");
                if (students != null) {
                    for (Student student : students) {
                        System.out.println("Номер группы: " + student.getGroupNumber() +
                                           ", Средний балл: " + student.getAverageGrade() +
                                           ", Номер зачетной книжки: " + student.getNumberOfRecordBook());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            case 3:
                System.out.println("Отсортированный список пользователей:");
                if (users != null) {
                    for (User user : users) {
                        System.out.println("Имя: " + user.getName() +
                                           ", Пароль: " + user.getPassword() +
                                           ", Почта: " + user.getEmail());
                    }
                } else {
                    System.out.println("Список пуст.");
                }
                break;
            default:
                System.err.println("Неверный выбор.");
                break;
        }
    }

    private static void performSearch(int typeChoice) {
        switch (typeChoice) {
            case 1:
                if (buses != null) {
                    System.out.println("Введите номер автобуса для поиска:");
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    Bus key = new Bus.Builder()
                            .setNumber(number)
                            .build();
                    int index = BinarySearch.binarySearch(buses, key);
                    if (index >= 0) {
                        System.out.println("Автобус найден на позиции: " + index);
                        writeFoundElementToFile(buses[index]);
                    } else {
                        System.err.println("Автобус не найден.");
                    }
                }
                break;
            case 2:
                // Аналогичный блок для студентов
                if (students != null) {
                    System.out.println("Введите номер группы студента для поиска:");
                    int groupNumber = scanner.nextInt();
                    scanner.nextLine();
                    Student key = new Student.Builder()
                            .setGroupNumber(groupNumber)
                            .build();
                    int index = BinarySearch.binarySearch(students, key);
                    if (index >= 0) {
                        System.out.println("Студент найден на позиции: " + index);
                        writeFoundElementToFile(students[index]);
                    } else {
                        System.err.println("Студент не найден.");
                    }
                }
                break;
            case 3:
                // Аналогичный блок для пользователей
                if (users != null) {
                    System.out.println("Введите пароль пользователя для поиска:");
                    int password = scanner.nextInt();
                    scanner.nextLine();
                    User key = new User.Builder()
                            .setPassword(password)
                            .build();
                    int index = BinarySearch.binarySearch(users, key);
                    if (index >= 0) {
                        System.out.println("Пользователь найден на позиции: " + index);
                        writeFoundElementToFile(users[index]);
                    } else {
                        System.out.println("Пользователь не найден.");
                    }
                }
                break;
            default:
                System.err.println("Неверный выбор.");
                break;
        }
    }

    private static void writeFoundElementToFile
            (Object element) {
        StringBuilder data = new StringBuilder();
        if (element instanceof Bus) {
            Bus bus = (Bus) element;
            data.append("Номер: ").append(bus.getNumber()).append(", Модель: ").append(bus.getModel()).append(", Пробег: ").append(bus.getMileage()).append("\n");
        } else if (element instanceof Student) {
            Student student = (Student) element;
            data.append("Номер группы: ").append(student.getGroupNumber()).append(", Средний балл: ").append(student.getAverageGrade()).append(", Номер зачетной книжки: ").append(student.getNumberOfRecordBook()).append("\n");
        } else if (element instanceof User) {
            User user = (User) element;
            data.append("Имя: ").append(user.getName()).append(", Пароль: ").append(user.getPassword()).append(", Почта: ").append(user.getEmail()).append("\n");
        }
        FileWriterUtil.writeToFile(FOUND_ELEMENT_FILE_PATH, data.toString());
        System.out.println("Найденный элемент записан в файл.");
    }

    private static void writeToFile() {
        StringBuilder data = new StringBuilder();
        if (buses != null) {
            for (Bus bus : buses) {
                data.append(bus.getNumber()).append(", ")
                        .append(bus.getModel()).append(", ")
                        .append(bus.getMileage()).append("\n");
            }
        }
        if (students != null) {
            for (Student student : students) {
                data.append(student.getGroupNumber()).append(", ")
                        .append(student.getAverageGrade()).append(", ")
                        .append(student.getNumberOfRecordBook()).append("\n");
            }
        }
        if (users != null) {
            for (User user : users) {
                data.append(user.getName()).append(", ")
                        .append(user.getPassword()).append(", ")
                        .append(user.getEmail()).append("\n");
            }
        }
        FileWriterUtil.writeToFile(OUTPUT_FILE_PATH, data.toString());
        System.out.println("Данные записаны в файл.");

//        scanner.close();
    }
}


