package aston.strategy;

import aston.core.BinarySearchable;
import aston.core.Sortable;

public class SearchStrategy<T extends Sortable<T> & BinarySearchable<T>> {

    private final T[] data;
    private final T target;

    public SearchStrategy(T[] data, T target) {
        this.data = data;
        this.target = target;
    }

    //соединяем сортировку и бинарный поиск
    public void execute() {
        //sort
        data[0].sort(data);
        //search
        int index = data[0].binarySearch(data,target);
        if (index >= 0)
            System.out.println("Found: " + data[index]);
        else
            System.out.println("Not found: " + target);
    }
}
