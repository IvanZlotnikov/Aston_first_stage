package aston.data;

import aston.model.User;
import aston.utils.FileReaderUtill;
import aston.strategy.DataFillerStrategy;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

//для заполнения данных пользователя
public class UserDataFiller implements DataFillerStrategy<User> {
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
            users[i] = new User.Builder()
                    .setName(name)
                    .setPassword(password)
                    .setEmail(email)
                    .build();
        }
        return users;
    }

    @Override
    public User[] fillDataManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей: ");
        int count ;
        try {
            count = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не цифру!!");
            return fillDataManually();
        }
        User[] users = new User[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Введите имя пользователя: ");
            String name = scanner.next();
            System.out.println("Введите пароль пользователя: ");
            String password = scanner.next();
            String email;
            boolean b = false;
            System.out.println("Введите свою електронную почту: ");
            do {
                email = scanner.nextLine();
                String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                b = email.matches(email_regex);
            } while (!b);
            {
                System.out.println("почта валидна !!!");
            }
            users[i] = new User.Builder()
                    .setName(name)
                    .setPassword(password)
                    .setEmail(email)
                    .build();
        }
        return users;
    }
}
