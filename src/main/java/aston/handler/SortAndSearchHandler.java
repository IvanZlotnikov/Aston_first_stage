package aston.handler;

import aston.algoritms.BinarySearch;
import aston.algoritms.CustomSort;
import aston.algoritms.SelectionSort;
import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;


//Сортировка и поиск элементов.
public class SortAndSearchHandler {
    public static void sort(int typeChoice, int sortChoice, int fieldChoice, Bus[] buses, Student[] students, User[] users) {
        switch (typeChoice) {
            case 1:
                if (buses == null) return;
                if (sortChoice == 1) {
                    SelectionSort.sort(buses);
                } else if (sortChoice == 2) {
                    switch (fieldChoice) {
                        case 1:
                            CustomSort.sort(buses, Bus::getNumber);
                            break;
                        case 2:
                            CustomSort.sort(buses, Bus::getModel);
                            break;
                        case 3:
                            CustomSort.sort(buses, Bus::getMileage);
                            break;
                        default:
                            System.err.println("Неверный выбор поля.");
                            return;
                    }
                }
                break;
            case 2:
                if (students == null) return;
                if (sortChoice == 1) {
                    SelectionSort.sort(students);
                } else if (sortChoice == 2) {
                    switch (fieldChoice) {
                        case 1:
                            CustomSort.sort(students, Student::getGroupNumber);
                            break;
                        case 2:
                            CustomSort.sort(students, Student::getAverageGrade);
                            break;
                        case 3:
                            CustomSort.sort(students, Student::getNumberOfRecordBook);
                            break;
                        default:
                            System.err.println("Неверный выбор поля.");
                            return;
                    }
                }
                break;
            case 3:
                if (users == null) return;
                if (sortChoice == 1) {
                    SelectionSort.sort(users);
                } else if (sortChoice == 2) {
                    switch (fieldChoice) {
                        case 1:
                            CustomSort.sort(users, User::getName);
                            break;
                        case 2:
                            CustomSort.sort(users, User::getPassword);
                            break;
                        case 3:
                            CustomSort.sort(users, User::getEmail);
                            break;
                        default:
                            System.err.println("Неверный выбор поля.");
                            return;
                    }
                }
                break;
        }
    }

    public static int search(int typeChoice, Bus[] buses, Student[] students, User[] users, Object key) {
        int index = -1;
        switch (typeChoice) {
            case 1:
                if (buses != null && key instanceof Bus) {
                    index = BinarySearch.binarySearch(buses, (Bus) key);
                }
                break;
            case 2:
                if (students != null && key instanceof Student) {
                    index = BinarySearch.binarySearch(students, (Student) key);
                }
                break;
            case 3:
                if (users != null && key instanceof User) {
                    index = BinarySearch.binarySearch(users, (User) key);
                }
                break;
            default:
                System.err.println("Неверный выбор");
                break;
        }
        return index != -1 ? index + 1 : index;
    }
}