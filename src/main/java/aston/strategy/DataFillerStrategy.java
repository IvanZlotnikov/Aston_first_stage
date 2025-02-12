package aston.strategy;

import aston.model.Bus;
import aston.model.Student;

//для заполнения данных.
public interface DataFillerStrategy<T> {

    T[] fillDataManually();
    T[] fillDataRandomly(int count);
    T[] fillDataFromFile();
}
