package aston.handler;


import aston.input.DataInput;


//для ввода данных (разных типов)
public class InputManager<T> {
    public T[] inputData(DataInput<T> dataInput, int size) {
        return dataInput.input(size);
    }
}
