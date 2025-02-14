package aston.input;

import aston.entity.Bus;
import aston.entity.Student;
import aston.entity.User;
import aston.utill.InputHandler;

public class ManualDataInput<T> implements DataInput<T> {
    private final Class<T> type;

    public ManualDataInput(Class<T> type) {
        this.type = type;
    }

    @Override
    public T[] input(int size) {
        if (Bus.class.isAssignableFrom(type)) {
            return (T[]) InputHandler.inputBusesManually(size);
        } else if (Student.class.isAssignableFrom(type)) {
            return (T[]) InputHandler.inputStudentsManually(size);
        } else if (User.class.isAssignableFrom(type)) {
            return (T[]) InputHandler.inputUsersManually(size);
        }
        throw new IllegalArgumentException("Unknown data type");
    }
}
