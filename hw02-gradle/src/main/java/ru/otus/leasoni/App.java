package ru.otus.leasoni;

import java.util.Collections;
import java.util.Comparator;

public class App {
    public static void main(String[] args) {
        DiyArrayList<Integer> testAddAllList = new DiyArrayList<>();
        Collections.addAll(testAddAllList, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
        System.out.println(testAddAllList.get(21));

        DiyArrayList<Integer> testList = new DiyArrayList<>(1000);
        DiyArrayList<Integer> testCopyList = new DiyArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            testList.add(i);
        }

        Collections.sort(testList, Comparator.reverseOrder());
        System.out.println(testList.get(999));

        for (int i = 1000; i > 0; i--) {
            testCopyList.add(i);
        }
        Collections.copy(testCopyList, testList);
        System.out.println(testCopyList.get(0));
    }
}
