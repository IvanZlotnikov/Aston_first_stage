package aston.data;

import aston.core.DataFiller;
import aston.model.Bus;
import aston.utils.FileReaderUtill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BusDataFiller implements DataFiller<Bus> {
    private String filePath;

    public BusDataFiller(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Bus[] fillData() {
        String[] lines = FileReaderUtill.readFile(filePath);
        Bus[] buses = new Bus[lines.length];
        Pattern pattern = Pattern.compile("(\\d+) (\\w+) (\\d+)");
        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                buses[i] = new Bus.Builder()
                        .setNumber(Integer.parseInt(matcher.group(0)))
                        .setModel(matcher.group(1))
                        .setMileage(Integer.parseInt(matcher.group(2)))
                        .build();
            }
        }
        return buses;
    }
}
