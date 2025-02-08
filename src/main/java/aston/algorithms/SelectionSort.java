package aston.algorithms;

import aston.core.Sortable;
import aston.model.Bus;
import aston.model.Student;
import aston.model.User;

//для сортировки выбором
public class SelectionSort<T> implements Sortable<T> {

    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (compare(array[j], array[minIndex]) < 0)
                    minIndex = j;
            }
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    private int compare(T a, T b) {
        if (a instanceof Bus && b instanceof Bus) {
            Bus busA = (Bus) a;
            Bus busB = (Bus) b;
            return Integer.compare(busA.getMileage(), busB.getMileage());
        } else if (a instanceof User && b instanceof User) {
            User userA = (User) a;
            User userB = (User) b;
            return userA.getName().compareTo(userB.getName());
        } else if (a instanceof Student && b instanceof Student) {
            Student studentA = (Student) a;
            Student studentB = (Student) b;
            return Double.compare(studentA.getAverageGrade(), studentB.getAverageGrade());
        }
        throw new IllegalArgumentException("Unsupported type");
    }
}
