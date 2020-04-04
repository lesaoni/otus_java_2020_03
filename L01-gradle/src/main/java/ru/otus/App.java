package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String... args) {
        List<Integer> collection = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            collection.add(i);
        }
    }
}
