package aston.model;

import aston.utils.UtilsToProject;
import aston.strategy.SearchStrategy;
import aston.strategy.SortStrategy;
import aston.strategy.Evenness;

//Представление автобуса
public class Bus implements Comparable<Bus>, SortStrategy<Bus>, SearchStrategy<Bus>, Evenness {

    private final int number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return String.format("Автобус №%-3d | Модель: %-10s | Пробег: %,d км", number, model, mileage);
    }


    @Override
    public int compareTo(Bus target) {
        if (target == null)
            throw new IllegalArgumentException("target == null");
        return Integer.compare(this.mileage, target.mileage);
    }
    
    @Override
    public boolean isEven() {
        return ((mileage % 2) == 0);
    }

    @Override
    public int searchFor(Bus[] array, Bus target) {
        return UtilsToProject.search(array, target);
    }

    @Override
    public void sort(Bus[] array) {
        UtilsToProject.sort(array);
    }

    public static class Builder {
        private int number;
        private String model;
        private int mileage;

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

}
