package aston.core;

import aston.algorithms.SelectionSort;
import aston.algorithms.BinarySearch;
import aston.data.BusDataFiller;
import aston.data.StudentDataFiller;
import aston.data.UserDataFiller;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("Главное меню");
                System.out.println("1 - Автобусы");
                System.out.println("2 - Студенты");
                System.out.println("3 - Пользователи");
                System.out.println("4 - Выход");
                System.out.print("Выберите пункт: ");
                String choice = reader.readLine().trim();
                if (choice.equals("4")) {
                    exit = true;
                    continue;
                }

                System.out.println("Выберите способ заполнения данных: 1 - Вручную, 2 - Рандомно, 3 - Из файла");
                String methodChoice = reader.readLine().trim();
                String method;
                switch (methodChoice) {
                    case "1":
                        method = "manual";
                        break;
                    case "2":
                        method = "random";
                        break;
                    case "3":
                        method = "file";
                        break;
                    default:
                        method = "manual";
                        break;
                }

                System.out.print("Введите количество элементов: ");
                int size = Integer.parseInt(reader.readLine().trim());

                switch (choice) {
                    case "1":
                        BusDataFiller busFiller = new BusDataFiller();
                        Bus[] buses = busFiller.fillData(size, method);
                        processAndSearch(buses, reader);
                        break;
                    case "2":
                        StudentDataFiller studentFiller = new StudentDataFiller();
                        Student[] students = studentFiller.fillData(size, method);
                        processAndSearch(students, reader);
                        break;
                    case "3":
                        UserDataFiller userFiller = new UserDataFiller();
                        User[] users = userFiller.fillData(size, method);
                        processAndSearch(users, reader);
                        break;
                    default:
                        System.out.println("Некорректный ввод, попробуйте снова.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    private static <T extends Comparable<T>> void processAndSearch(T[] array, BufferedReader reader) throws IOException {
        System.out.println("Заданный массив: " + Arrays.toString(array));
        SelectionSort.sort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        System.out.println("Введите значение для бинарного поиска: ");
        String searchValue = reader.readLine().trim();

        T key = parseSearchValue(array, searchValue);
        if (key == null) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
            return;
        }

        int index = BinarySearch.search(array, key);
        if (index == -1) {
            System.out.println("Элемент не найден.");
        } else {
            System.out.println("Элемент найден: " + array[index]);
        }
    }

    private static <T extends Comparable<T>> T parseSearchValue(T[] array, String searchValue) {
        if (array.length == 0) return null;

        Object sample = array[0];

        if (sample instanceof Student) {
            try {
                int groupNumber = Integer.parseInt(searchValue);
                return (T) new Student.Builder().setGroupNumber(groupNumber).build();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число для номера группы.");
            }
        } else if (sample instanceof Bus) {
            try {
                int mileage = Integer.parseInt(searchValue);
                return (T) new Bus.Builder().setMileage(mileage).build();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число для пробега.");
            }
        } else if (sample instanceof User) {
            return (T) new User.Builder().setName(searchValue).build();
        }

        return null;
    }


}


//public class Main {
//    private static int foundDataIndex = 0;
//
//    public static void main(String[] args) {
//        DataFiller<Bus> busFiller = new BusDataFiller("buses.txt");
//        DataFiller<User> userFiller = new UserDataFiller("users.txt");
//        DataFiller<Student> studentFiller = new StudentDataFiller("students.txt");
//
//        Bus[] buses = busFiller.fillData();
//        User[] users = userFiller.fillData();
//        Student[] students = studentFiller.fillData();
//
//        String[] foundData = new String[100];
//
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//            boolean exit = false;
//            DataValidator validator = new DataValidator();
//            FileWriterUtill fileWriter = new FileWriterUtill();
//
//            //clear all files
//            fileWriter.clearFile("output_buses.txt");
//            fileWriter.clearFile("output_users.txt");
//            fileWriter.clearFile("output_students.txt");
//            //скорее всего его чистить не нужно
////            fileWriter.clearFile("found_data.txt");
//
//            Map<Integer, Consumer<BufferedReader>> actions = new HashMap<>();
//            actions.put(1, br -> {
//                try {
//                    System.out.println("Введите пробег для поиска: ");
//                    int mileage = Integer.parseInt(br.readLine());
//                    Bus busTarget = new Bus.Builder().setMileage(mileage).build();
//                    SearchStrategy<Bus> busSearch = new SearchStrategy<>(buses, busTarget,foundData,foundDataIndex);
//                    busSearch.execute();
//                } catch (IOException e) {
//                    System.out.println("Ошибка ввода: " + e.getMessage());
//                }
//            });
//            actions.put(2, br -> {
//                try {
//                    System.out.println("Введите имя для поиска: ");
//                    String name = br.readLine();
//                    User userTarget = new User.Builder().setName(name).build();
//                    SearchStrategy<User> userSearch = new SearchStrategy<>(users, userTarget,foundData,foundDataIndex);
//                    userSearch.execute();
//                } catch (IOException e) {
//                    System.out.println("Ошибка ввода: " + e.getMessage());
//                }
//            });
//            actions.put(3, br -> {
//                try {
//                    System.out.println("Введите средний бал для поиска: ");
//                    double averageGrade = Double.parseDouble(br.readLine());
//                    Student studentTarget = new Student.Builder().setAverageGrade(averageGrade).build();
//                    SearchStrategy<Student> studentSearch = new SearchStrategy<>(students, studentTarget,foundData,foundDataIndex);
//                    studentSearch.execute();
//                } catch (IOException e) {
//                    System.out.println("Ошибка ввода: " + e.getMessage());
//                }
//            });
//            actions.put(4, br -> {
//                for (Bus bus : buses) {
//                    if (validator.validate(bus.toString())) {
//                        fileWriter.writeToFile(bus.toString(), "output_buses.txt", true);
//                    }
//                }
//                for (User user : users) {
//                    if (validator.validate(user.toString())) {
//                        fileWriter.writeToFile(user.toString(), "output_users.txt", true);
//                    }
//                }
//                for (Student student : students) {
//                    if (validator.validate(student.toString())) {
//                        fileWriter.writeToFile(student.toString(), "output_students.txt", true);
//                    }
//                }
//                for (int i = 0; i < foundDataIndex; i++) {
//                    fileWriter.writeToFile(foundData[i], "found_data.txt", true);
//                }
//                System.out.println("Данные записанны в файл.");
//            });
//
//            while (!exit) {
//                System.out.println("Выберите действие: ");
//                System.out.println("1. Сортировка и поиск автобусов");
//                System.out.println("2. Сортировка и поиск пользователей");
//                System.out.println("3. Сортировка и поиск студентов");
//                System.out.println("4. Записать данные в файл");
//                System.out.println("5. Выйти");
//
//                try {
//                    int choice = Integer.parseInt(bufferedReader.readLine());
//                    Consumer<BufferedReader> action = actions.get(choice);
//                    if (action != null)
//                        action.accept(bufferedReader);
//                    else if (choice == 5)
//                        exit = true;
//                    else
//                        System.out.println("Неверный выбор попробуйте снова.");
//                } catch (IOException e) {
//                    System.out.println("Ошибка ввода : " + e.getMessage());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Ошибка с BufferedReader: " + e.getMessage());
//        }
//    }
//
//    public static int getFoundDataIndex() {
//        return foundDataIndex;
//    }
//
//    public static void incrementFoundDataIndex(){
//        foundDataIndex++;
//    }
//}
