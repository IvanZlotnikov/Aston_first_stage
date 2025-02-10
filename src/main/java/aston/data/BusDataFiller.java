package aston.data;

import aston.model.Bus;
import aston.utils.FileReaderUtill;
import aston.strategy.DataFillerStrategy;


public class BusDataFiller implements DataFillerStrategy<Bus> {
    private String filePath;

    public BusDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Bus[] fillData() {
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
}
