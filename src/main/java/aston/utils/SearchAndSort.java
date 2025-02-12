package aston.utils;

import aston.core.Main;
import aston.strategy.SearchStrategy;
import aston.strategy.SortStrategy;

//для сортировки и поиска
public class SearchAndSort<T extends SortStrategy<T> & SearchStrategy<T>> {

    private final T[] data;
    private final T target;
    private final String[] foundData;

    public SearchAndSort(T[] data, T target, String[] foundData, int foundDataIndex) {
        this.data = data;
        this.target = target;
        this.foundData = foundData;
    }

    //соединяем сортировку и бинарный поиск
    public void execute() {
        //sort
        data[0].sort(data);
        //search
        int index = data[0].searchFor(data, target);
        if (index >= 0) {
            System.out.println("Found: " + data[index]);
            foundData[Main.getFoundDataIndex()] = data[index].toString();
            Main.incrementFoundDataIndex();
        } else
            System.out.println("Not found: " + target);
    }
}
