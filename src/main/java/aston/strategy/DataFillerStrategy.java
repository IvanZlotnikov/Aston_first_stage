package aston.strategy;

//для заполнения данных.
public interface DataFillerStrategy<T> {

    T[] fillData();

}
