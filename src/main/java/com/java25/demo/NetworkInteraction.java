package com.java25.demo;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * NetworkInteraction
 *
 * A compact demonstration class that runs several example interactions with public HTTP/WebSocket endpoints.
 * The examples mirror the kinds of examples shown in the Java HTTP Client (Java 11+) tutorials: sync GET,
 * async GET, POST (JSON + form), download binary to file, and a simple WebSocket echo.
 *
 * Notes:
 * - Uses java.net.http.HttpClient (available since Java 11).
 * - Keeps methods small and synchronous from the caller's point of view. Asynchronous examples use
 *   CompletableFuture and are joined before returning.
 * - Intended for experimentation. Update endpoint URIs if you prefer other test servers.
 *
 * source: https://www.baeldung.com/java-9-http-client
 */
public class NetworkInteraction {

    static void main() {
        HttpRequest httpRequest = null;
        HttpResponse<String> httpResponse = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://postman-echo.com/get"))
                    .GET()
                    .build();
            httpResponse = HttpClient.newBuilder().build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IO.println(httpResponse.body());
    }


}

