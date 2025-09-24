package com.java25.demo;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.stream.*;


/*
  STEPS TO RUN JAVA FLIGHT RECORDING USING JDK 25
*
*  1) Generate .class files using javac LatencyDemoOld.java

   2) Start flight recording with below command:
   /Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home/bin/java \
  -XX:StartFlightRecording:filename=latencydemo.jfr,duration=60s,settings=profile \
  -XX:StartFlightRecording:duration=60s,jdk.MethodTiming \
  -cp src/main/java com.java25.demo.LatencyDemo


   3) And finally execute:
   /Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home/bin/jfr view method-timing latencydemo.jfr

   3 commands after that :

javac LatencyDemo.java

java -XX:StartFlightRecording:method-timing=LatencyDemo,filename=latencydemo.jfr LatencyDemo

jfr view method-timing latencydemo.jfr

*
*
*
*
*
* */


public class LatencyDemo {


    void main() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(LatencyDemo::cpuJob);
        executor.submit(LatencyDemo::ioJob);
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
    }
    private static void cpuJob() {
        long count = IntStream.range(1, 200_000)
                .filter(LatencyDemo::isPrime)
                .count();
        IO.println(count);
    }
    private static void ioJob() {
        try {
            Path file = Paths.get("iojob.txt");
            try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                for (int i = 0; i < 20; i++) {
                    writer.write("Line " + i + "\n");
                    Thread.sleep(50);
                }
            }

            try (BufferedReader reader = Files.newBufferedReader(file)) {
                while (reader.readLine() != null) {
                    Thread.sleep(30);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}