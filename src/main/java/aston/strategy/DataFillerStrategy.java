package aston.strategy;

//для заполнения данных.
public interface DataFillerStrategy<T> {

    T[] fillDataFromFile();
    T[] fillDataRandomly(int count);
    T[] fillDataManually();

}
