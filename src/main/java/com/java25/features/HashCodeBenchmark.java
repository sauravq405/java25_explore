package com.java25.features;

public class HashCodeBenchmark {

    private static final int COUNT = 200_000_000;

    // Critical for the JDK 25 optimization:
    // This is a compile-time constant String literal → the JVM can 100% prove it never changes
    private static final String KEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) {
        // JIT warm-up — run a few times so the loop gets fully optimized
        benchmark();
        benchmark();

        long start = System.nanoTime();
        benchmark();  // This is where the magic happens (or doesn't) depending on JDK version
        long end = System.nanoTime();

        System.out.printf("Time: %.1f ms%n", (end - start) / 1_000_000.0);
    }

    static long benchmark() {
        long sum = 0;
        for (int i = 0; i < COUNT; i++) {
            // Before JDK 25:
            //   → hashCode() recomputes the hash from scratch every single call
            //     (loops over all 62 characters → 62×31 multiplications & additions)
            //
            // In JDK 25 and later:
            //   → The JIT detects KEY is a true constant → precomputes the hash ONCE at class loading
            //   → Every single KEY.hashCode() call is replaced with a literal constant!
            //      (e.g. sum += 1394138775; — completely free)
            //
            // Result: 200_000_000 hash computations turn into 200_000_000 integer additions
            sum += KEY.hashCode();
        }
        return sum; // prevents dead-code elimination
    }
}
