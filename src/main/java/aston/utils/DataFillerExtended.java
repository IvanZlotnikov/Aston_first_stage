package aston.utils;

import aston.core.DataFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public interface DataFillerExtended<T> extends DataFiller<T> {

    T[] fillData(int size, String method);

    static String getUserChoice() {
        System.out.println("Выберите способ заполнения данных: 1 - Вручную, 2 - Рандомно, 3 - Из файла");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String choice = reader.readLine().trim();
            if (choice.equals("1")) return "manual";
            if (choice.equals("2")) return "random";
            if (choice.equals("3")) return "file";
        } catch (IOException e) {
            System.out.println("Ошибка ввода, попробуйте снова.");
        }
        return "manual";
    }

    static List<String> readFileData(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return lines;
    }

    T createFromString(String data);

    default T[] generateRandomData(int size) {
        List<T> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataList.add(createFromString("RandomData" + random.nextInt(1000)));
        }
        return dataList.toArray((T[]) new Object[dataList.size()]);
    }

    default T[] fillDataManually(int size) {
        List<T> dataList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < size; i++) {
                System.out.println("Введите данные для элемента " + (i + 1) + ":");
                String input = reader.readLine();
                dataList.add(createFromString(input));
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        return dataList.toArray((T[]) new Object[dataList.size()]);
    }

    default T[] fillDataFromFile(String filePath) {
        List<String> lines = readFileData(filePath);
        List<T> objects = new ArrayList<>();
        for (String line : lines) {
            objects.add(createFromString(line));
        }
        return objects.toArray((T[]) new Object[objects.size()]);
    }
}