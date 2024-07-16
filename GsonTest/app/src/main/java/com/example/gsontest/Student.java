package com.example.gsontest;

public class Student<E extends Job> {

    private String name = "张三";

    private transient int age = 25;

    private E e;

    public void setE(E e) {
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public String getName() {
        return name;
    }
}
