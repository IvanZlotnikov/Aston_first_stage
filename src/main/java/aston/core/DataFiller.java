package aston.core;

//для заполнения данных.
public interface DataFiller<T> {

    T[] fillDataFromFile();
    T[] fillDataRandomly(int count);
    T[] fillDataManually();

}
