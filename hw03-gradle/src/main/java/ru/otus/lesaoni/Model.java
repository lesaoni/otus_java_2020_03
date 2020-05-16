package ru.otus.lesaoni;

public class Model {

    private int a;
    private int b;

    public Model(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getSum() {
        return this.a + this.b;
    }
    public int getMultiply() {return this.a * this.b;}
}
