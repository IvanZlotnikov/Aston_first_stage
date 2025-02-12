package aston.strategy;

//для реализации алгоритмов бинарного поиска
public interface SearchStrategy<T> {

    int searchFor(T[] array, T target);

}
