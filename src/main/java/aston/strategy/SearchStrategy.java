package aston.strategy;

public interface SearchStrategy<T> {
    
    public int SearchFor(T[] array, T key);
}
