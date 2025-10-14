package com.java25.features;

public class ThreadLocalDemo {

    static void main() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("Chrome");

        Thread t1 = new Thread(() -> {
            threadLocal.set("Chrome-1");
            System.out.println("user-1: "+threadLocal.get());
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Chrome-2");
            System.out.println("user-2: "+threadLocal.get());
        });

        t1.start();
        t2.start();
    }
}
