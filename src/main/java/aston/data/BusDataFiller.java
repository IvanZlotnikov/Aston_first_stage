package aston.data;


import aston.model.Bus;

public class BusDataFiller extends AbstractDataFiller<Bus> {
    @Override
    public Bus createFromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных: " + data);
        }
        return new Bus.Builder()
                .setNumber(Integer.parseInt(parts[0].trim()))
                .setModel(parts[1].trim())
                .setMileage(Integer.parseInt(parts[2].trim()))
                .build();
    }

    @Override
    public Bus[] generateRandomData(int size) {
        Bus[] buses = new Bus[size];
        for (int i = 0; i < size; i++) {
            buses[i] = new Bus.Builder()
                    .setNumber(Integer.parseInt("Bus" + (i + 1)))
                    .setModel("Model" + random.nextInt(100))
                    .setMileage(random.nextInt(50000))
                    .build();
        }
        return buses;
    }

    @Override
    public Bus[] fillDataFromFile(int size) {
        // Заглушка для чтения из файла
        System.out.println("Чтение автобусов из файла пока не реализовано.");
        return new Bus[0];
    }
}
