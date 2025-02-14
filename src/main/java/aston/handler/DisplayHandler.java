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
                    System.out.println("Список автобусов:");
                    for (int i = 0; i < buses.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(buses[i]));
                    }
                }
                break;
            case 2:
                if (students == null) {
                    System.out.println("Список студентов пуст.");
                } else {
                    System.out.println("Список студентов:");
                    for (int i = 0; i < students.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(students[i]));
                    }
                }
                break;
            case 3:
                if (users == null) {
                    System.out.println("Список пользователей пуст.");
                } else {
                    System.out.println("Список пользователей:");
                    for (int i = 0; i < users.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(users[i]));
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
                    for (int i = 0; i < buses.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(buses[i]));
                    }
                }
                break;
            case 2:
                if (students == null) {
                    System.out.println("Список студентов пуст.");
                } else {
                    System.out.println("Отсортированный список студентов:");
                    for (int i = 0; i < students.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(students[i]));
                    }
                }
                break;
            case 3:
                if (users == null) {
                    System.out.println("Список пользователей пуст.");
                } else {
                    System.out.println("Отсортированный список пользователей:");
                    for (int i = 0; i < users.length; i++) {
                        System.out.println((i + 1) + ". " + FileManager.buildDataString(users[i]));
                    }
                }
                break;
        }
    }
}