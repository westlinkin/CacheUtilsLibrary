package com.lifeofcoding.cacheutilslibrary_sample;

/**
 * Created by Wesley Lin on 9/6/15.
 */
public class MyClass {
    private String text;
    private int id;
    private boolean isIdZero;

    public MyClass(String text, int id) {
        this.text = text;
        this.id = id;
        this.isIdZero = id == 0;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public boolean isIdZero() {
        return isIdZero;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsIdZero(boolean isIdZero) {
        this.isIdZero = isIdZero;
    }

    @Override
    public String toString() {
        return "{text: " + text + ", id: " + id + ", isIdZero: " + isIdZero + "}";
    }

    public static MyClass SAMPLE_MYCLASS_1 = new MyClass("MyClass Sample1", 0);
    public static MyClass SAMPLE_MYCLASS_2 = new MyClass("MyClass Sample2", 1);
    public static MyClass SAMPLE_MYCLASS_3 = new MyClass("MyClass Sample3", 1);
}
