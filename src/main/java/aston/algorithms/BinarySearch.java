package aston.algorithms;

import aston.core.BinarySearchable;
import aston.core.ComparableModel;

//для бинарного поиска
public class BinarySearch<T extends ComparableModel> implements BinarySearchable<T> {

    @Override
    public int binarySearch(T[] array, T target) {
        if (array == null || target == null)
            throw new IllegalArgumentException("The given array is null!");
        if (array.length == 0)
            throw new IllegalArgumentException("The given array is empty!");
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid].compareTo(target) < 0)
                left = mid + 1;
            else if (array[mid].compareTo(target) > 0)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
