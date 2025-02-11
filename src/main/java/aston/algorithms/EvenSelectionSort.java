package aston.algorithms;

import aston.strategy.SortStrategy;
import aston.strategy.Evenness;

// сортировка выбором для четных элементов
public class EvenSelectionSort<T extends Comparable & Evenness> implements SortStrategy<T> {

    @Override
    public void sort(T[] array) {
        if (array == null)
            throw new IllegalArgumentException("Array is null");
        if (array.length == 0)
            throw new IllegalArgumentException("Array is empty");

        for (int i = 0; i < array.length - 1; i++) {
            if (!array[i].isEven())
                continue; 
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j].compareTo(array[minIndex]) < 0 && array[j].isEven())
                    minIndex = j;
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
}
