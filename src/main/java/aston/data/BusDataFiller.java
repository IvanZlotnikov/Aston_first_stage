package aston.data;


import aston.model.Bus;
import aston.utils.DataFillerExtended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class BusDataFiller implements DataFillerExtended<Bus> {
    @Override
    public Bus[] fillData(int size, String method) {
        switch (method) {
            case "manual":
                return fillDataManually(size);
            case "random":
                return generateRandomData(size);
            case "file":
                System.out.println("Введите путь к файлу:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String filePath = reader.readLine();
                    return fillDataFromFile(filePath);
                } catch (IOException e) {
                    System.out.println("Ошибка чтения пути файла: " + e.getMessage());
                }
        }
        return new Bus[0];
    }

    @Override
    public Bus createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных: " + data);
        }
        return new Bus.Builder()
                .setNumber(parts[0].trim())
                .setModel(parts[1].trim())
                .setMileage(Integer.parseInt(parts[2].trim()))
                .build();
    }

    @Override
    public Bus[] generateRandomData(int size) {
        Random random = new Random();
        List<Bus> buses = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buses.add(new Bus.Builder()
                    .setNumber("Bus" + (i + 1))
                    .setModel("Model" + random.nextInt(100))
                    .setMileage(random.nextInt(10000))
                    .build());
        }
        return buses.toArray(new Bus[0]);
    }

    @Override
    public Bus[] fillDataManually(int size) {
        List<Bus> buses = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < size; i++) {
                System.out.println("Введите номер, модель, пробег через запятую:");
                String input = reader.readLine();
                buses.add(createFromString(input));
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        return buses.toArray(new Bus[0]);
    }

    @Override
    public Bus[] fillDataFromFile(String filePath) {
        return DataFillerExtended.super.fillDataFromFile(filePath);
    }

    @Override
    public Bus[] fillData() {
        return new Bus[0];
    }
}