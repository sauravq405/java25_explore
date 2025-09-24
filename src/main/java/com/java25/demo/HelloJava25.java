package com.java25.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class HelloJava25 {
    void main() {
        System.out.println("Welcome to Java 25");
        System.out.println("JVM started in " + ManagementFactory.getRuntimeMXBean().getUptime()
                + " ms on Java " + Runtime.version().feature());
    }
}
