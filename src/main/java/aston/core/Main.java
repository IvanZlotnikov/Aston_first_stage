package aston.core;

import aston.data.BusDataFiller;
import aston.data.StudentDataFiller;
import aston.data.UserDataFiller;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;
import aston.utils.DataFillerExtended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("Выберите тип данных: 1 - Автобусы, 2 - Студенты, 3 - Пользователи, 4 - Выход");
                String choice = reader.readLine().trim();
                if (choice.equals("4")) {
                    exit = true;
                    continue;
                }

                System.out.println("Введите количество элементов:");
                int size = Integer.parseInt(reader.readLine().trim());

                String method = DataFillerExtended.getUserChoice();

                switch (choice) {
                    case "1":
                        BusDataFiller busFiller = new BusDataFiller();
                        Bus[] buses = busFiller.fillData(size, method);
                        System.out.println("Автобусы загружены: ");
                        for (Bus bus : buses) {
                            System.out.println(bus);
                        }
                        break;
                    case "2":
                        StudentDataFiller studentFiller = new StudentDataFiller();
                        Student[] students = studentFiller.fillData(size, method);
                        System.out.println("Студенты загружены: ");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        break;
                    case "3":
                        UserDataFiller userFiller = new UserDataFiller();
                        User[] users = userFiller.fillData(size, method);
                        System.out.println("Пользователи загружены: ");
                        for (User user : users) {
                            System.out.println(user);
                        }
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
            }
        }
    }
}
