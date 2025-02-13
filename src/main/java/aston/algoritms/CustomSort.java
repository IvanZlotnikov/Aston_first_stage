package aston.algoritms;

import java.util.function.ToIntFunction;

public class CustomSort {
    public static <T extends Comparable<T>> void sort(T[] array, ToIntFunction<T> keyExtractor) {
        for (int i = 0; i < array.length - 1; i += 2) { // Идем по четным индексам массива
            int minIndex = i;
            for (int j = i + 2; j < array.length; j += 2) { // Идем по четным индексам массива
                if (keyExtractor.applyAsInt(array[j]) < keyExtractor.applyAsInt(array[minIndex])) {
                    minIndex = j;
                }
            }
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
