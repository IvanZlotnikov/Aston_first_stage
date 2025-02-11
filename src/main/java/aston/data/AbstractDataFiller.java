package aston.data;

import aston.core.DataFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.lang.reflect.Array;


public abstract class AbstractDataFiller<T> implements DataFiller<T> {
    protected final Random random = new Random();
    private final Class<T> type; // Добавляем хранение класса

    protected AbstractDataFiller(Class<T> type) {
        this.type = type;
    }

    public abstract T createFromString(String data);

    public T[] fillData(int size, String method) {
        if (method.equals("manual")) {
            return fillDataManually(size);
        } else if (method.equals("random")) {
            return generateRandomData(size);
        } else if (method.equals("file")) {
            return fillDataFromFile(size);
        } else {
            return (T[]) Array.newInstance(type, 0);
        }
    }

    protected abstract T[] generateRandomData(int size);

    protected abstract T[] fillDataFromFile(int size);

    protected T[] fillDataManually(int size) {
        T[] array = (T[]) Array.newInstance(type, size); // Правильное создание массива
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < size; i++) {
                while (true) {
                    try {
                        System.out.println("Введите данные для объекта " + (i + 1) + ":");
                        String input = reader.readLine();
                        array[i] = createFromString(input);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage() + " Попробуйте снова.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        return array;
    }
}