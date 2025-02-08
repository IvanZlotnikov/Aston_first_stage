package aston.strategy;

public interface SearchStrategy<T> {
    
    public int binarySearch(T[] array, T key);
}
