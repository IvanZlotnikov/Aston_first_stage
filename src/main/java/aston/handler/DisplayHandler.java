package aston.handler;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;


//корманный логер
public class DisplayHandler {

    public static void displayList(int typeChoice, Bus[] buses, Student[] students, User[] users) {
        switch (typeChoice) {
            case 1:
                if (buses == null) {
                    System.out.println("Список автобусов пуст.");
                } else {
                    for (Bus bus : buses) {
                        System.out.println(FileManager.buildDataString(bus));
                    }
                }
                break;
            case 2:
                if (students == null) {
                    System.out.println("Список студентов пуст.");
                } else {
                    for (Student student : students) {
                        System.out.println(FileManager.buildDataString(student));
                    }
                }
                break;
            case 3:
                if (users == null) {
                    System.out.println("Список пользователей пуст.");
                } else {
                    for (User user : users) {
                        System.out.println(FileManager.buildDataString(user));
                    }
                }
                break;
        }
    }

    public static void displaySortedElements(int typeChoice, Bus[] buses, Student[] students, User[] users) {
        switch (typeChoice) {
            case 1:
                if (buses == null) {
                    System.out.println("Список автобусов пуст.");
                } else {
                    System.out.println("Отсортированный список автобусов:");
                    for (Bus bus : buses) {
                        System.out.println(FileManager.buildDataString(bus));
                    }
                }
                break;
            case 2:
                if (students == null) {
                    System.out.println("Список студентов пуст.");
                } else {
                    System.out.println("Отсортированный список студентов:");
                    for (Student student : students) {
                        System.out.println(FileManager.buildDataString(student));
                    }
                }
                break;
            case 3:
                if (users == null) {
                    System.out.println("Список пользователей пуст.");
                } else {
                    System.out.println("Отсортированный список пользователей:");
                    for (User user : users) {
                        System.out.println(FileManager.buildDataString(user));
                    }
                }
                break;
        }
    }
}