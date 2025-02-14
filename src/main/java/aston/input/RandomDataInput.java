package aston.input;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;
import aston.utill.RandomDataGenerator;

//для генерации случайных данных
public class RandomDataInput<T> implements DataInput<T> {
    private final Class<T> type;

    public RandomDataInput(Class<T> type) {
        this.type = type;
    }

    @Override
    public T[] input(int size) {
        if (Bus.class.isAssignableFrom(type)) {
            return (T[]) RandomDataGenerator.generateRandomBuses(size);
        } else if (Student.class.isAssignableFrom(type)) {
            return (T[]) RandomDataGenerator.generateRandomStudents(size);
        } else if (User.class.isAssignableFrom(type)) {
            return (T[]) RandomDataGenerator.generateRandomUsers(size);
        }
        throw new IllegalArgumentException("Unknown data type");
    }
}

