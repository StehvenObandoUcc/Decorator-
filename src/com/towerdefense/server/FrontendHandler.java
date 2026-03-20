package com.towerdefense.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handler for serving the frontend HTML file
 */
public class FrontendHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            byte[] response = Files.readAllBytes(Paths.get("public/index.html"));
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        } catch (IOException e) {
            String errorMsg = "Error: index.html not found. Make sure public/index.html exists.";
            exchange.sendResponseHeaders(500, errorMsg.length());
            OutputStream os = exchange.getResponseBody();
            os.write(errorMsg.getBytes());
            os.close();
        }
    }
}
