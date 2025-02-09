package aston.strategy;

import aston.core.BinarySearchable;
import aston.core.Sortable;
import aston.utils.FileWriterUtill;

public class SearchStrategy<T extends Sortable<T> & BinarySearchable<T>> {

    private final T[] data;
    private final T target;
    private final FileWriterUtill fileWriter = new FileWriterUtill();

    public SearchStrategy(T[] data, T target) {
        this.data = data;
        this.target = target;
    }

    //соединяем сортировку и бинарный поиск
    public void execute() {
        //sort
        data[0].sort(data);
        //search
        int index = data[0].binarySearch(data, target);
        if (index >= 0) {
            System.out.println("Found: " + data[index]);
            fileWriter.writeToFile(data[index].toString(), "found_data.txt");
        } else
            System.out.println("Not found: " + target);
    }
}
