package aston.utill;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;

import java.util.Scanner;

//Ручной ввод данных через консоль.
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static Bus[] inputBusesManually(int count) {
        Bus[] buses = new Bus[count];
        for (int i = 0; i < count; i++) {

            int number;
            do {System.out.println("введите номер автобуса: ");
                while (!scanner.hasNextInt()){
                    System.out.println("Это не номер!!!");
                    scanner.next();
                }
                number=scanner.nextInt();
            }while (number<=0);;
            System.out.println("Введите модель автобуса: ");
            String model = scanner.next();
            int mileage;
            do {System.out.println("введите пробег автобуса ");
                while (!scanner.hasNextInt()){
                    System.out.println("Это не пробег!!!");
                    scanner.next();
                }
                mileage=scanner.nextInt();
            }while (mileage<=0);;

            buses[i] = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        }
        return buses;
    }

    public static User[] inputUsersManually(int count) {
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
                    .setPassword(Integer.parseInt(password))
                    .setEmail(email)
                    .build();
        }
        return users;
    }

    public static Student[] inputStudentsManually(int size) {
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите данные для студента " + (i + 1));
            System.out.print("Номер группы: ");
            int groupNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Средний балл: ");
            double averageGrade = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Номер зачетной книжки: ");
            int numberOfRecordBook = scanner.nextInt();
            scanner.nextLine();

            if (DataValidator.validateStudentData(groupNumber, averageGrade, numberOfRecordBook)) {
                students[i] = new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageGrade(averageGrade)
                        .setNumberOfRecordBook(numberOfRecordBook)
                        .build();
            } else {
                System.err.println("Некорректные данные, попробуйте снова.");
                i--; // повторите ввод для тек студента
            }
        }
        return students;
    }
}
