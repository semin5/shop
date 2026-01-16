package com.apple.shop.other;

interface Callback{
    void callback();
}

public class MyClass {
    public static void main(String[] args) {
        함수1(() -> System.out.println("world"));
    }

    static void 함수1(Callback 함수2){
        System.out.println("hello");
        함수2.callback();
    }
}
