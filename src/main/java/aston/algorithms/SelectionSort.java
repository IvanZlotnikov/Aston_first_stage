package aston.algorithms;

import aston.strategy.SortStrategy;

//для сортировки выбором
public class SelectionSort<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(T[] array) {
        
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
          
            // выставляем минималиное значение
            int min = i;

            // ищем в массиве настоящий минимум
            for (int j = i + 1; j < n; j++) {
                if (array[min].compareTo(array[j]) > 0) {
                  
                    // обновляем переменную при его нахождении
                    min = j;
                }
            }

            // перемещаем элемент в правильную позицию
            T temp = array[i];
            array[i] = array[min];
            array[min] = temp;           
        }
    }
}
