package aston.core;

//для заполнения данных.
public interface DataFiller<T> {

//    T[] fillData();
        T[] fillData(int size, String method);


}
