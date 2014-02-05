/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.burdun.laba7;

/**
 *
 * @author Slava
 */
public interface Queue {
    
    // Добавить в конец очереди
    void offer(Object e);
    
    // Взять без удаления, элемент из очереди
    Object peek();
    
    // Взять и удалить элемент из очереди
    Object poll();
}
