package aston.handler;

import java.util.Scanner;
//Обработка меню и ввода данных.
public class MenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMainMenu() {
        System.out.println("Выберите тип ввода данных:");
        System.out.println("1. Заполнение массива из файла");
        System.out.println("2. Генерация случайных данных");
        System.out.println("3. Ввод данных вручную");
        System.out.println("4. Выход");
        return scanner.nextInt();
    }

    public static int showDataTypeMenu() {
        System.out.println("Выберите тип данных для заполнения:");
        System.out.println("1. Автобусы");
        System.out.println("2. Студенты");
        System.out.println("3. Пользователи");
        System.out.println("4. Выход");
        return scanner.nextInt();
    }

    public static int getSizeFromUser() {
        System.out.println("Введите количество элементов:");
        return scanner.nextInt();
    }
}
