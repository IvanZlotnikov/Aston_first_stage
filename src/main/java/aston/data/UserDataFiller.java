package aston.data;


import aston.model.User;

public class UserDataFiller extends AbstractDataFiller<User> {
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
            users[i] = new User.Builder()
                    .setName("User" + (i + 1))
                    .setPassword("Pass" + random.nextInt(1000))
                    .setEmail("user" + (i + 1) + "@example.com")
                    .build();
        }
        return users;
    }

    @Override
    public User[] fillDataFromFile(int size) {
        // Заглушка для чтения из файла
        System.out.println("Чтение пользователей из файла пока не реализовано.");
        return new User[0];
    }
}
