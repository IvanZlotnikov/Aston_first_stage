package aston.model;

import aston.core.BinarySearchable;
import aston.core.Sortable;
import aston.utils.UtilsToProject;


public class Bus implements Comparable<Bus>, Sortable<Bus>, BinarySearchable<Bus> {

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
        return "Bus{" +
               "number='" + number + '\'' +
               ", model='" + model + '\'' +
               ", mileage=" + mileage +
               '}';
    }

    @Override
    public int compareTo(Bus target) {
        if (target == null)
            throw new IllegalArgumentException("target == null");
        return Integer.compare(this.mileage, target.mileage);
    }


    @Override
    public int binarySearch(Bus[] array, Bus target) {
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
