package aston.utils;
import java.util.Arrays;

public class UtilsToProject {
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null)
            throw new IllegalArgumentException("argument of array is null");
        if (array.length == 0)
            throw new IllegalArgumentException("array is empty");
        T[] sortedArray = Arrays.stream(array)
                .sorted(T::compareTo)
                .toArray(size -> Arrays.copyOf(array, size));
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }

    public static <T extends Comparable<T>> int search(T[] array, T target) {
        if (array == null)
            throw new IllegalArgumentException("argument of array is null");
        if (array.length == 0)
            throw new IllegalArgumentException("array is empty");
        return Arrays.stream(array)
                .filter(elem -> elem.compareTo(target) == 0)
                .findFirst()
                .map(Arrays.asList(array)::indexOf)
                .orElse(-1);
    }
}

