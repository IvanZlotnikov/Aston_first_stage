//package aston.strategy;
//
//import aston.core.BinarySearchable;
//import aston.core.Main;
//import aston.core.Sortable;
//
//public class SearchStrategy<T extends Sortable<T> & BinarySearchable<T>> {
//
//    private final T[] data;
//    private final T target;
//    private final String[] foundData;
//
//    public SearchStrategy(T[] data, T target, String[] foundData, int foundDataIndex) {
//        this.data = data;
//        this.target = target;
//        this.foundData = foundData;
//    }
//
//    //соединяем сортировку и бинарный поиск
//    public void execute() {
//        //sort
//        data[0].sort(data);
//        //search
//        int index = data[0].binarySearch(data, target);
//        if (index >= 0) {
//            System.out.println("Found: " + data[index]);
//            foundData[Main.getFoundDataIndex()] = data[index].toString();
//            Main.incrementFoundDataIndex();
//        } else
//            System.out.println("Not found: " + target);
//    }
//}
