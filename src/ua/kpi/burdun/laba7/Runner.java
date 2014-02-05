/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.burdun.laba7;

import java.util.Random;

/**
 *
 * @author Slava
 */
public class Runner {
    
        public static void main(String[] args) {
        
        Random rand = new Random();
        
        // Testing of methods working with MyLinkedList
        System.out.println("Testing of methods working with MyLinkedList:");        
        System.out.println();
        MyList linkedList = new MyLinkedList();
        
        for (int i = 0; i < 10; i++) {
            linkedList.add(rand.nextInt(10));
        }
        System.out.println("linkedList:");
        System.out.println(linkedList);
        System.out.println();
        System.out.println("sort(linkedList)");
        MyCollections.sort(linkedList);
        System.out.println(linkedList);
        System.out.println();
        
        System.out.println("swap(linkedList, 0, 5)");
        MyCollections.swap(linkedList, 0, 5);
        System.out.println(linkedList);
        System.out.println();
        
        MyLinkedList linkedList2 = new MyLinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList2.add(0);
        }
        System.out.println("linkedList2:");
        System.out.println(linkedList2);
        System.out.println("copy(linkedList2, linkedList)");
        MyCollections.copy(linkedList2, linkedList);
        System.out.println("linkedList2:");
        System.out.println(linkedList2);
        System.out.println();
        
        System.out.println("reverse(linkedList)");
        MyCollections.reverse(linkedList);
        System.out.println(linkedList);
        System.out.println();
        System.out.println();
        
        
        // Testing of methods working with MyArrayList
        System.out.println("Testing of methods working with MyArrayList:");        
        System.out.println();
        MyList arrayList = new MyArrayList();
        
        for (int i = 0; i < 10; i++) {
            arrayList.add(rand.nextInt(10));
        }
        System.out.println("arrayList:");
        System.out.println(arrayList);
        System.out.println();
        System.out.println("sort(arrayList)");
        MyCollections.quickSort(arrayList);
        System.out.println(arrayList);
        System.out.println();
        
        System.out.println("swap(arrayList, 0, 5)");
        MyCollections.swap(arrayList, 0, 5);
        System.out.println(arrayList);
        System.out.println();
        
        MyArrayList arrayList2 = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList2.add(0);
        }
        System.out.println("arrayList2:");
        System.out.println(arrayList2);
        System.out.println("copy(arrayList2, arrayList)");
        MyCollections.copy(arrayList2, arrayList);
        System.out.println("arrayList2:");
        System.out.println(arrayList2);
        System.out.println();
        
        System.out.println("reverse(arrayList)");
        MyCollections.reverse(arrayList);
        System.out.println(arrayList);
        System.out.println();

        System.out.println("sort(arrayList)");
        MyCollections.quickSort(arrayList);
        System.out.println(arrayList);
        System.out.println("binarySearch(arrayList, 5)");
        System.out.println(MyCollections.binarySearch(arrayList, 5));
    }
}
