package aston.algoritms;

public class BinarySearch {
    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid].compareTo(key) < 0) {
                low = mid + 1;
            } else if (array[mid].compareTo(key) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
