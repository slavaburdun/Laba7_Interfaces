package ua.kpi.burdun.laba7;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class MyLinkedList implements MyList, Queue, Stack{
    
    private int size;         // item of elements on queue
    private Element first;    // beginning of queue
    private Element last;     // end of queue

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    
    // helper linked list class
    private static class Element {

        private Integer item;
        private Element next;
        private Element prev;

        Element(Element prev, Integer item, Element next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public boolean hasNext() {
            return (next != null);
        }

        public Element next() {
            return next;
        }

        public Integer getElement() {
            return item;
        }

        public void setElement(Integer number) {
            this.item = number;
        }
    }

    
    
//------------------------------- Private methods
    
    private boolean isEmpty() {
        return size == 0;
    }

    private void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException("MyLinkedList doesn't has element ith such index");
        }
    }
    
    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("MyLinkedList doesn't has element ith such index");
        }
    }

    private Element element(int index) {
        Element x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private Integer unlinkFirst(Element f) {
        final Integer element = f.item;
        final Element next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }
    
    
//---------------------------- Add methods
    
    // Вставляет элемент в конец списка
    @Override
    public void add(Object element) {
        this.addLast(element);
    }
    
    // Вставляет элемент на указанную позицию, сдвигая остальные элементы
    @Override
    public void add(int index, Object element) {   
        checkPositionIndex(index);

        if (index == size) {
            addLast(element);
        }
        else {
            Element succ = element(index);
            final Element pred = succ.prev;
            final Element newNode = new Element(pred, (Integer)element, succ);
            succ.prev = newNode;
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            size++;
        }
    }
    
    public void addFirst(Object element) {
        final Element f = first;
        final Element newElement = new Element(null, (Integer)element, f);
        first = newElement;
        if (f == null)
            last = newElement;
        else
            f.prev = newElement;
        size++;    
    }
    
    public void addLast(Object element) {
        final Element l = last;
        final Element newElement = new Element(l, (Integer)element, null);
        last = newElement;
        if (l == null)
            first = newElement;
        else
            l.next = newElement;
        size++;
    }

    @Override
    public boolean addAll(Object[] c) {
        return addAll(size, c);
    }
    
    @Override
    public boolean addAll(int index, Object[] c) {
        checkPositionIndex(index);

        int numNew = c.length;
        if (numNew == 0)
            return false;

        Element pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = element(index);
            pred = succ.prev;
        }

        for (Object o : c) {
            @SuppressWarnings("unchecked") Integer e = (Integer) o;
            Element newNode = new Element(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        return true;
    }
    
    
//----------------------------- Get methods
    
    @Override
    public Integer get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("MyLinkedList underflow");
        }
        checkElementIndex(index);
        return element(index).item;
    }

    public Integer getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("MyLinkedList underflow");
        }
        return first.item;
    }

    public Integer getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("MyLinkedList underflow");
        }
        return last.item;
    }

    
    
//------------------------------ Remove methods
    
    @Override
    public Integer remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("MyLinkedList underflow");
        }
        checkElementIndex(index);
        Integer item = element(index).item;
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            element(index-1).next = element(index).next;
        }        
        return item;
    }

    public Integer removeFirst() {
        final Element f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    public Integer removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("MyLinkedList underflow");
        }
        Integer item = last.item;        
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            Element prevElement = element(size-1);
            prevElement.next = null;
            last = prevElement;
        }        
        return item;
    }

    

//----------- Other overriding methods from interface "MyList"
    
    // Изменяет значение элемента
    @Override
    public Integer set(int index, Object element) {   
        checkElementIndex(index);
        Element e = element(index);
        Integer oldValue = e.item;
        e.item = (Integer)element;
        return oldValue;
    }
    
    
    @Override
    public int size() {
        return size;
    }
    
    
    // Поиск индекса по значению
    @Override
    public int indexOf(Object o) {                 
        int index = 0;
        if (o == null) {
            for (Element x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Element x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Element x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }
    
    
    @Override
    public String toString() {
        String result = new String();
//        result += "all: ";
        for (Element x = first; x != null; x = x.next) {
            if (x.next != null) {
                result += x.item + " -> ";
            }
            else {
                result += x.item;
            }
        }
        return result;
    }
    

    
//------------- Overriding methods from interface "Queue"
    
    // Добавить в конец очереди
    @Override
    public void offer(Object e) {
        add(e);
    }
    
    // Взять без удаления, элемент из очереди
    @Override
    public Object peek(){
        final Element f = first;
        return (f == null) ? null : f.item;
    }
    
    // Взять и удалить элемент из очереди
    @Override
    public Object poll(){
        final Element f = first;
        return (f == null) ? null : unlinkFirst(f);
    }
    
    
    
//------------- Overriding methods from interface "Stack"
    
    // Поместить элемент в стек
    public void push(Object e) {
        addFirst(e);
    }
    
    // Взять элемент из стека
    public Object pop() {
        return removeFirst();
    }

}