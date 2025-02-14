package aston.algoritms;

import java.util.Comparator;
import java.util.function.Function;

//для сортировки массивов по определенному ключу
public class CustomSort {
    public static <T, U extends Comparable<? super U>> void sort(T[] array, Function<? super T, ? extends U> keyExtractor) {
        Comparator<T> comparator = Comparator.comparing(keyExtractor);
        sort(array, comparator);
    }

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length - 1; i += 2) { // Идем по четным индексам массива
            int minIndex = i;
            for (int j = i + 2; j < array.length; j += 2) { // Идем по четным индексам массива
                if (comparator.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
