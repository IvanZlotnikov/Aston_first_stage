package aston.handler;


import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;
import aston.utill.FileDataLoader;

//Управление данными (загрузка, генерация, ручной ввод).
public class DataManager {

    private static Bus[] buses;
    private static Student[] students;
    private static User[] users;

    public static void handleFileChoice(int typeChoice, int size, String busesFilePath, String studentsFilePath, String usersFilePath) {
        switch (typeChoice) {
            case 1:
                buses = FileDataLoader.loadBusesFromFile(busesFilePath, size);
                break;
            case 2:
                students = FileDataLoader.loadStudentsFromFile(studentsFilePath, size);
                break;
            case 3:
                users = FileDataLoader.loadUsersFromFile(usersFilePath, size);
                break;
            default:
                System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                break;
        }
    }

    public static Bus[] getBuses() {
        return buses;
    }

    public static void setBuses(Bus[] newBuses) {
        buses = newBuses;
    }

    public static Student[] getStudents() {
        return students;
    }

    public static void setStudents(Student[] newStudents) {
        students = newStudents;
    }

    public static User[] getUsers() {
        return users;
    }

    public static void setUsers(User[] newUsers) {
        users = newUsers;
    }
}