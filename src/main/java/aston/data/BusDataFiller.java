package aston.data;

import aston.core.DataFiller;
import aston.model.Bus;

public class BusDataFiller implements DataFiller<Bus> {
    @Override
    public Bus[] fillData() {
        return new Bus[]{
                new Bus.Builder().setNumber("123").setModel("ModelA").setMileage(1500).build(),
                new Bus.Builder().setNumber("456").setModel("ModelB").setMileage(1000).build(),
                new Bus.Builder().setNumber("789").setModel("ModelC").setMileage(2000).build()
        };
    }
}
