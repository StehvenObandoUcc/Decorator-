package com.towerdefense.server;

import com.towerdefense.game.GameEngine;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Handler for returning the current game state as JSON
 */
public class GameStateHandler implements HttpHandler {
    private GameEngine gameEngine;
    
    public GameStateHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String jsonResponse = gameEngine.getGameStateJson();
        
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
        
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes());
        os.close();
    }
}
