package ua.kpi.burdun.laba7;

import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Arrays;

/**
 *
 * @author Slava
 */
public class MyArrayList implements MyList, RandomAccess {
    
    private transient Object[] elementData;
    private int size;   // - размер списка
    
    
//----------------------- Constructors    

    // Constructs an empty list with an initial capacity of ten (10)
    public MyArrayList() {     
        this.elementData = new Object[10];
    }
    
    // Constructs an empty list with the specified initial capacity
    public MyArrayList(int initialCapacity) { 
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
    }
    

    
//----------------------- Private methods
    
    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }
    
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
     
    
    
    
//--- Overriding methods from interface "MyList" and some other public methods

    // Вставляет элемент в конец списка
    @Override
    public void add(Object e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
    }

    // Вставляет элемент на указанную позицию, сдвигая остальные элементы
    @Override
    public void add(int index, Object element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
    
    // Appends all of the elements to the end of this list
    @Override
    public boolean addAll(Object[] c) {
        int numNew = c.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(c, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }
    
    // Inserts all of the elements into this list, starting at the specified position
    @Override
    public boolean addAll(int index, Object[] c) {
        rangeCheckForAdd(index);

        int numNew = c.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        System.arraycopy(c, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }
    
    
    /* Increases the capacity of this ArrayList instance, if necessary, to
     * ensure that it can hold at least the number of elements specified by 
     * the minimum capacity argument.
    */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > 0)
            ensureCapacityInternal(minCapacity);
    }
    
    
    @Override
    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }
    
    
    // При удалении размер массива должен уменьшаться
    @Override
    public Object remove(int index) {
        rangeCheck(index);

        Object oldValue = elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // Let gc do its work

        return oldValue;
    }
    
    
    // Изменяет значение элемента
    @Override
    public Object set(int index, Object element) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }


    //Поиск индекса по значению
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    
    
    @Override
    public int size() {
        return size;
    }
    
    
    @Override
    public String toString() {
        String result = new String();
//        result += "Array list: ";
        for (int i = 0; i < size; i++) {
            result += elementData[i] + " ";
        }
        return result;
    }

    
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

}
