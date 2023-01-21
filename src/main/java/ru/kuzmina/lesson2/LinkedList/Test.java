package ru.kuzmina.lesson2.LinkedList;

public class Test {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        System.out.println(myLinkedList);

        myLinkedList.delete(3);
        System.out.println(myLinkedList);

        myLinkedList.add(4);
        System.out.println(myLinkedList);

        myLinkedList.add(11, 3);
        System.out.println(myLinkedList);

        System.out.println(myLinkedList.get(3).getValue());
    }
}
