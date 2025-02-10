package aston.data;

import aston.core.DataFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractDataFiller<T> implements DataFiller<T> {
    protected final Random random = new Random();

    public abstract T createFromString(String data);

    public T[] fillData(int size, String method) {
        if (method.equals("manual")) {
            return fillDataManually(size);
        } else if (method.equals("random")) {
            return generateRandomData(size);
        } else if (method.equals("file")) {
            return fillDataFromFile(size);
        } else {
            return (T[]) new Comparable[0];
        }
    }

    protected abstract T[] generateRandomData(int size);

    protected abstract T[] fillDataFromFile(int size);

    protected T[] fillDataManually(int size) {
        T[] array = (T[]) new Comparable[size];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < size; i++) {
                System.out.println("Введите данные для объекта " + (i + 1) + ":");
                String input = reader.readLine();
                array[i] = createFromString(input);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        return array;
    }
}
