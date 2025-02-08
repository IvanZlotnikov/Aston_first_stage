package aston.algorithms;

import aston.strategy.SearchStrategy;

//для бинарного поиска
public class BinarySearch<T extends Comparable<T>> implements SearchStrategy<T> {

    @Override
    public int SearchFor(T[] array, T key) {
        
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Если искомый объект в середине
            if (array[mid].equals(key))
                return mid;

            // Если искомый объект больше, идем вправо
            if (array[mid].compareTo(key) < 0)
                low = mid + 1;

            // Если искомый объект меньше, идем влево
            else
                high = mid - 1;
        }

        // В случае отсутствия искомого объекта попадаем сюда
        return -1;
    }
}
