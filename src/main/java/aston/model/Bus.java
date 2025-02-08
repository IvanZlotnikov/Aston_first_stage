package aston.model;

class Bus {
    private final short number;
    private final String model;
    private final int mileage;

    private Bus(short number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public short getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    // Builder класс
    public static class BusBuilder {
        private short number;
        private String model;
        private int mileage;

        public BusBuilder number(short number) {
            this.number = number;
            return this;
        }

        public BusBuilder model(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        Bus build() {
            if (number <= 0) {
                throw new IllegalArgumentException("Номер автобуса не может быть меньше нуля или равен нулю");
            }
            if (model == null || model.isEmpty()) {
                throw new IllegalArgumentException("Модель автобуса не может быть пустой");
            }
            if (mileage < 0) {
                throw new IllegalArgumentException("Пробег не может быть отрицательным");
            }
            return new Bus(number, model, mileage);
        }
    }
}
