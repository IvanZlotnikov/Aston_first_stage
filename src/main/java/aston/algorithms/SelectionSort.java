package aston.algorithms;

import aston.core.Sortable;

//для сортировки выбором
public class SelectionSort<T extends Comparable> implements Sortable<T> {

    @Override
    public void sort(T[] array) {
        if (array == null)
            throw new IllegalArgumentException("Array is null");
        if (array.length == 0)
            throw new IllegalArgumentException("Array is empty");

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j].compareTo(array[minIndex]) < 0)
                    minIndex = j;
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
