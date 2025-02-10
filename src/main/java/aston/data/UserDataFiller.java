package aston.data;

import aston.core.DataFiller;
import aston.model.User;
import aston.utils.FileReaderUtill;

import java.util.Random;
import java.util.Scanner;

public class UserDataFiller implements DataFiller<User> {
    private final String filePath;

    public UserDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public User[] fillDataFromFile() {
        String[] lines = FileReaderUtill.readFile(filePath);
        User[] users = new User[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splits = lines[i].split(",");
            users[i] = new User.Builder()
                    .setName(splits[0])
                    .setPassword(splits[1])
                    .setEmail(splits[2])
                    .build();
        }
        return users;
    }

    @Override
    public User[] fillDataRandomly(int count) {
        Random random = new Random();
        User[] users = new User[count];
        for (int i = 0; i < count; i++) {
            String name = "User" + random.nextInt(100);
            String password = "password" + random.nextInt(100);
            String email = "email" + random.nextInt(100) + "@gmail.com";
            users[i] = new User.Builder().setName(name).setPassword(password).setEmail(email).build();
        }
        return users;
    }

    @Override
    public User[] fillDataManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of user: ");
        int count = scanner.nextInt();
        User[] users = new User[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Enter user name: ");
            String name = scanner.next();
            System.out.println("Enter user password: ");
            String password = scanner.next();
            System.out.println("Enter user email: ");
            String email = scanner.next();
            users[i] = new User.Builder().setName(name).setPassword(password).setEmail(email).build();
        }
        return users;
    }
}
