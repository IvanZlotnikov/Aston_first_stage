package aston.data;


import aston.model.User;
import aston.utils.DataFillerExtended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UserDataFiller implements DataFillerExtended<User> {
    private final Random random = new Random();

    @Override
    public User[] fillData(int size, String method) {
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
        return new User[0];
    }

    @Override
    public User createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных: " + data);
        }
        return new User.Builder()
                .setName(parts[0].trim())
                .setPassword(parts[1].trim())
                .setEmail(parts[2].trim())
                .build();
    }

    @Override
    public User[] generateRandomData(int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            String name = "User" + (i + 1);
            String password = "Pass" + random.nextInt(1000);
            String email = name.toLowerCase() + "@example.com";
            users[i] = new User.Builder()
                    .setName(name)
                    .setPassword(password)
                    .setEmail(email)
                    .build();
        }
        return users;
    }

    @Override
    public User[] fillDataManually(int size) {
        return DataFillerExtended.super.fillDataManually(size);
    }

    @Override
    public User[] fillDataFromFile(String filePath) {
        return DataFillerExtended.super.fillDataFromFile(filePath);
    }


    @Override
    public User[] fillData() {
        return new User[0];
    }
}
