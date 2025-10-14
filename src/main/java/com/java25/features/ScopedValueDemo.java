package com.java25.features;

import java.util.UUID;

public class ScopedValueDemo {

    private static final ScopedValue<String> requestId = ScopedValue.newInstance();

     void main() {
          handleRequest(UUID.randomUUID().toString());
    }

    private void handleRequest(String reqId) {
         ScopedValue.where(requestId, reqId).run(() -> {
             log("Start processing request...");
             //simulate some process
             authenticate();
             try {
                 fetchData();
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             log("Finish processing request...");
         });
    }

    private void fetchData() throws InterruptedException {
        log("Fetching data from DB...");

        //Capture the current request ID
        String currentReqId = requestId.get();

        //Re-Bind it explicitly inside child thread
        Thread t = new Thread(() ->
                ScopedValue.where(requestId, currentReqId).run(() ->
                        log("Child thread fetching data...")
                        )
                );
        t.start();
        t.join(); //wait for child
    }

    private void authenticate() {
        log("Authenticating user...");
    }

    private void log(String message) {
         IO.println("["+requestId.get()+"] "+message);
    }
}
