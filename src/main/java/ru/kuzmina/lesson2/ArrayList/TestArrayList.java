package ru.kuzmina.lesson2.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        System.out.println(arrayList);

        arrayList.remove(2);
        System.out.println(arrayList);
        arrayList.remove(0);
        System.out.println(arrayList);

        arrayList.add(11, 2);
        System.out.println(arrayList);

        System.out.println("arrayList[4] = " + arrayList.get(4));
        System.out.println("индекс элемента {11} = " + arrayList.indexOf(11));

    }
}
