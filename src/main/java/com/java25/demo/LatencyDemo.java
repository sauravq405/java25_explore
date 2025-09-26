package com.java25.demo;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.stream.*;


/*
  STEPS TO RUN JAVA FLIGHT RECORDING USING JDK 25
*
*

(Use the fully qualified class name for the selector and enable profile settings (which enable more profiling events))
3 commands after that :

javac -d . LatencyDemo.java

java -XX:StartFlightRecording:method-timing=com.java25.demo.LatencyDemo,filename=latencydemo.jfr com.java25.demo.LatencyDemo

jfr view method-timing latencydemo.jfr

#############################################################################################################################################################################################
##########################################################Troubleshooting####################################################################################################################
#############################################################################################################################################################################################

jfr summary latencydemo.jfr

If you prefer to run until the app exits (no duration), use dumponexit=true so file is written without manual jcmd:
java -XX:StartFlightRecording:settings=profile,method-timing=com.java25.demo.LatencyDemo,duration=30s,filename=latencydemo.jfr,dumponexit=true -cp . com.java25.demo.LatencyDemo

If methods are short or inlined, make them observable:
Alternative: reduce inlining (debug only):
# discourage inlining (slower, for testing)
java -XX:StartFlightRecording:settings=profile,method-timing=com.java25.demo.LatencyDemo,filename=latencydemo.jfr,dumponexit=true -XX:MaxInlineSize=0 -cp . com.java25.demo.LatencyDemo


jfr summary latencydemo.jfr         # see counts; look for jdk.MethodTiming > 0
jfr print --events jdk.MethodTiming latencydemo.jfr   # print method timing events
# or, if none, try:
jfr print --events jdk.MethodSample latencydemo.jfr
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