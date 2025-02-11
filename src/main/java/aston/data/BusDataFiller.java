package aston.data;

import aston.model.Bus;
import aston.utils.FileReaderUtill;
import aston.strategy.DataFillerStrategy;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// заполнение данными Автобус
public class BusDataFiller implements DataFillerStrategy<Bus> {
    private final String filePath;

    public BusDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Bus[] fillDataFromFile() {
        String[] lines = FileReaderUtill.readFile(filePath);
        Bus[] buses = new Bus[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] splits = lines[i].split(",");
            buses[i] = new Bus.Builder()
                    .setNumber(Integer.parseInt(splits[0]))
                    .setModel(splits[1])
                    .setMileage(Integer.parseInt(splits[2]))
                    .build();
        }
        return buses;
    }

    @Override
    public Bus[] fillDataRandomly(int count) {
        Random random = new Random();
        Bus[] buses = new Bus[count];
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(100);
            String model = "Model" + random.nextInt(100);
            int mileage = random.nextInt(10000) + 1000;
            buses[i] = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        }
        return buses;
    }

    @Override
    public Bus[] fillDataManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов: ");
        int count ;
        try {
            count = scanner.nextInt();
        } catch (
                InputMismatchException e) {
            System.out.println("Вы ввели не цифру!!");
            return fillDataManually();
        }


        Bus[] buses = new Bus[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Введите номер автобуса: ");
            int number = scanner.nextInt();
            System.out.println("Введите модель автобуса: ");
            String model = scanner.next();
            System.out.println("Введите пробег автобуса: ");
            int mileage = scanner.nextInt();
            buses[i] = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        }
        return buses;
    }
}
