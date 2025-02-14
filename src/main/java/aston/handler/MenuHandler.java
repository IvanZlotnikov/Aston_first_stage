package aston.handler;

import java.util.InputMismatchException;
import java.util.Scanner;
//Консоль
public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMainMenu() {
        while (true) {
            try {
                System.out.println("Выберите тип ввода данных:");
                System.out.println("1. Заполнение массива из файла");
                System.out.println("2. Генерация случайных данных");
                System.out.println("3. Ввод данных вручную");
                System.out.println("4. Выход");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Неверный ввод. Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка ввода
            }
        }
    }

    public static int showDataTypeMenu() {
        while (true) {
            try {
                System.out.println("Выберите тип данных для заполнения:");
                System.out.println("1. Автобусы");
                System.out.println("2. Студенты");
                System.out.println("3. Пользователи");
                System.out.println("4. Выход");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.err.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Неверный ввод. Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка ввода
            }
        }
    }

    public static int getSizeFromUser() {
        while (true) {
            try {
                System.out.println("Введите количество элементов:");
                int size = scanner.nextInt();
                if (size > 0) {
                    return size;
                } else {
                    System.err.println("Количество элементов должно быть положительным числом.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Неверный ввод. Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка ввода
            }
        }
    }
}