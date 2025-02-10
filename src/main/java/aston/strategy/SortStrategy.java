package aston.strategy;

//для реализации алгоритмов сортировки
public interface SortStrategy<T> {

    void sort(T[] array);
}
