package ru.otus.lesaoni;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> homework1 = new ArrayList<>();
        homework1.add("Hello, Otus!");
        System.out.println(Lists.reverse(homework1));
    }
}
