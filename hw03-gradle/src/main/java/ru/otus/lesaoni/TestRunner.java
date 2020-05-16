package ru.otus.lesaoni;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner<T> {

    private Class<T> clazz;
    private ArrayList<Method> testMethods = new ArrayList<>();
    private ArrayList<Method> beforeMethods = new ArrayList<>();
    private ArrayList<Method> afterMethods = new ArrayList<>();
    private int passedCount;
    private int failedCount;

    public TestRunner(Class<T> clazz) {
        this.clazz = clazz;
        fillMethodCollections();
    }

    public void runTest() {
        ArrayList<Method> innerWithoutExceptions = new ArrayList<>(testMethods);
        try {
            for (Method testMethod : testMethods) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                try {
                    for (Method befores : beforeMethods) {
                        befores.invoke(instance);
                    }
                } catch (Exception e) {
                    System.out.println("Can't run before methods. Aborting. Reason: ");
                    e.printStackTrace();
                }
                innerWithoutExceptions.remove(testMethod);
                testMethod.invoke(instance);
                passedCount++;
                try {
                    for (Method afters : afterMethods) {
                        afters.invoke(instance);
                    }
                } catch (Exception e) {
                    System.out.println("After methods throws exception. Aborting. Reason: ");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            failedCount++;
            testMethods = innerWithoutExceptions;
            e.printStackTrace();
            runTest();
        }

        if(innerWithoutExceptions.isEmpty()) {
            printResults();
        }
    }

    private void printResults() {
        System.out.println("Test count: " + (passedCount + failedCount) + " Passed tests: " + passedCount + " Failed tests: " + failedCount);
    }

    private void fillMethodCollections() {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }

}
