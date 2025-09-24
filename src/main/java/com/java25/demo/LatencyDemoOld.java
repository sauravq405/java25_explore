package com.java25.demo;

//After java 25
// LatencyDemoOld.java
//path = /Users/saurseng/Documents/Git_java25_explore/java25_explore/src/main/java/com/java25/demo
// /usr/libexec/java_home -V

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

*
*
*
*
*
* */



public class LatencyDemoOld {

    public static void main(String[] args) throws Exception {
        LatencyDemoOld demo = new LatencyDemoOld();

        // warm-up
        for (int i = 0; i < 3; i++) {
            demo.cpuJob(20_000);
        }

        // repeated runs to create measurable samples
        for (int run = 0; run < 20; run++) {
            demo.cpuJob(50_000);      // CPU-bound work
        }

        // one I/O-style job (simulated)
        demo.ioJob();

        System.out.println("Done");
    }

    // CPU-heavy work: test primality in a range
    public void cpuJob(int max) {
        for (int i = 2; i < max; i++) {
            isPrime(i);
        }
    }

    // Simulated I/O / blocking work
    public void ioJob() {
        try {
            Thread.sleep(1500); // pretend disk/network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        int r = (int)Math.sqrt(n);
        for (int i = 2; i <= r; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
