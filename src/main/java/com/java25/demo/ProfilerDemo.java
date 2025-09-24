package com.java25.demo;

//Before JDK 25
public class ProfilerDemo {

    public static void main(String[] args) {
        ProfilerDemo demo = new ProfilerDemo();
        demo.cpuJob();
        demo.ioJob();
    }

    // Simulate CPU-intensive task
    private void cpuJob() {
        for (int i = 2; i < 200_000; i++) {
            isPrime(i);
        }
    }

    // Simulate I/O-intensive task (just sleeping here)
    private void ioJob() {
        try {
            Thread.sleep(1500); // pretend it’s disk or network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
