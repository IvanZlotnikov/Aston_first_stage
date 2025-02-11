package aston.core;


import aston.data.BusDataFiller;
import aston.data.StudentDataFiller;
import aston.data.UserDataFiller;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                if ("4".equals(choice)) {
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
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    private static <T extends Comparable<T>> void processAndSearch(T[] array, BufferedReader reader) throws IOException {
        System.out.println("Заданный массив: " + Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        System.out.println("Введите значение для бинарного поиска: ");
        String searchValue = reader.readLine().trim();

        T key = parseSearchValue(array, searchValue);
        if (key == null) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
            return;
        }

        int index = Arrays.binarySearch(array, key);
        if (index < 0) {
            System.out.println("Элемент не найден.");
        } else {
            System.out.println("Элемент найден: " + array[index]);
        }
    }

    private static <T extends Comparable<T>> T parseSearchValue(T[] array, String searchValue) {
        if (array.length == 0) return null;

        Object sample = array[0];

        try {
            if (sample instanceof Student) {
                int groupNumber = Integer.parseInt(searchValue.trim());
                return (T) new Student.Builder().setGroupNumber(groupNumber).build();
            } else if (sample instanceof Bus) {
                int mileage = Integer.parseInt(searchValue.trim());
                return (T) new Bus.Builder().setMileage(mileage).build();
            } else if (sample instanceof User) {
                return (T) new User.Builder().setName(searchValue.trim()).build();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Некорректный формат данных. Введите корректное значение.");
        }

        return null;
    }
}