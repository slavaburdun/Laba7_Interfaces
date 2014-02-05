package ua.kpi.burdun.laba7;

import java.util.Random;
import java.util.RandomAccess;
import java.util.Collections;

/**
 *
 * @author Slava
 */
public class MyCollections {

    public static void sort(MyList list) {
        if (list instanceof RandomAccess) {
            quickSort(list);
        }
        else {
            bubbleSort(list);
        }
    }
    
    /*
     * Swaps the elements at the specified positions in the specified list
     */
    public static void swap(MyList list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
    
    /*
     * Copies all of the elements from one list into another
     */
    public static void copy(MyList dest, MyList src) {
        int srcSize = src.size();
        if (srcSize > dest.size()) {
            throw new IndexOutOfBoundsException("Source does not fit in dest");
        }
        for (int i = 0; i < srcSize; i++) {
            dest.set(i, src.get(i));
        }
    }
    
    /*
     * Reverses the order of the elements in the specified list
     */
    public static void reverse(MyList list) {
        int size = list.size();
        for (int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--) {
            swap(list, i, j);
        }
    }
    
    
//------------------- Methods working with MyLinkedList    

    public static void bubbleSort(MyList list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if ((Integer)list.get(j) < (Integer)list.get(min)) {
                    min = j;
                }
            }
            if (min != i) {
                swap(list, i, min);
            }
        }
    }

    
    
//------------------- Methods working with MyArrayList
    
    public static void quickSort(MyList list) {
        quicksort(list, 0, list.size()-1);
    }
    
    private static void quicksort(MyList list, int left, int right) {
        int index = partition(list, left, right);
        if (left < index - 1) {
            quicksort(list, left, index - 1);
        }
        if (index < right) {
            quicksort(list, index, right);
        }
    }

    private static int partition(MyList list, int left, int right) {
        int i = left, j = right;
        Integer pivot = (Integer) list.get((left + right) / 2);

        while (i <= j) {
            while ((Integer) list.get(i) < pivot) {
                i++;
            }
            while ((Integer) list.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        };
        return i;
    }

    
    /*
     * Метод реализует рекурсивный поиск в отсортированном массиве методом
     * бисекции.
     * Если элемент найден, то возвращается его индекс. Если не найден, то 
     * возвращается отрицательное значение, вычисляемое по следующей формуле: 
     * (-(insertion point) - 1), где insertion point - индекс того места в 
     * массиве, где должен был бы быть указанный элемент.
     */
    public static int binarySearch(MyList list, Integer key) {
        return BinarySearch_Rec(list, key, 0, list.size());
    }

    static int BinarySearch_Rec(MyList list, Integer key, int left, int right) {
        int mid = left + (right - left) / 2;

        if (left >= right) {
            return -(1 + left);
        }

        if (list.get(left) == key)
            return left;
        
        if (list.get(mid) == key) {
            if (mid == left + 1)
                return mid;
            else
                return BinarySearch_Rec(list, key, left, mid + 1);
        }
        
        else if ((Integer)list.get(mid) > key)
            return BinarySearch_Rec(list, key, left, mid);
        else
            return BinarySearch_Rec(list, key, mid + 1, right);
        
    }
    
}
