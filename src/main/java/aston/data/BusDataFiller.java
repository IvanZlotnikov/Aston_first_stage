package aston.data;


import aston.model.Bus;
import java.lang.reflect.Array;

public class BusDataFiller extends AbstractDataFiller<Bus> {
    public BusDataFiller() {
        super(Bus.class);
    }

    @Override
    public Bus createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных. Введите: номер, модель, пробег.");
        }

        try {
            int number = Integer.parseInt(parts[0].trim());
            String model = parts[1].trim();
            int mileage = Integer.parseInt(parts[2].trim());

            return new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка парсинга чисел. Убедитесь, что номер и пробег - это числа.");
        }
    }

    @Override
    public Bus[] generateRandomData(int size) {
        Bus[] buses = (Bus[]) Array.newInstance(Bus.class, size);
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(900) + 100;
            String model = "Model" + random.nextInt(100);
            int mileage = random.nextInt(50000);

            buses[i] = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        }
        return buses;
    }

    @Override
    public Bus[] fillDataFromFile(int size) {
        System.out.println("Чтение автобусов из файла пока не реализовано.");
        return new Bus[0];
    }
}
