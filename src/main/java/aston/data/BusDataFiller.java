package aston.data;

import aston.core.DataFiller;
import aston.model.Bus;
import aston.utils.FileReaderUtill;


public class BusDataFiller implements DataFiller<Bus> {
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
                    .setNumber(splits[0])
                    .setModel(splits[1])
                    .setMileage(Integer.parseInt(splits[2]))
                    .build();
        }
        return buses;
    }
}
