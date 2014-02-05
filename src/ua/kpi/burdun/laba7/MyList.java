package ua.kpi.burdun.laba7;

/**
 *
 * @author Slava
 */
public interface MyList {
    
    void add(Object e);
    
    void add(int index, Object element);
    
    boolean addAll(Object[] c);
    
    boolean addAll(int index, Object[] c);
    
    Object get(int index);
    
    Object remove(int index);
    
    Object set(int index, Object element);
    
    //Поиск индекса по значению
    int indexOf(Object o);
    
    int size();
    
    Object[] toArray();
}
