package com.towerdefense.server;

import com.towerdefense.game.GameEngine;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Handler for applying decorator upgrades to the tower
 */
public class UpgradeHandler implements HttpHandler {
    private String upgradeType;
    private GameEngine gameEngine;
    
    public UpgradeHandler(String type, GameEngine gameEngine) {
        this.upgradeType = type;
        this.gameEngine = gameEngine;
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            gameEngine.applyUpgrade(upgradeType);
            
            String response = "{\"status\":\"success\",\"upgrade\":\"" + upgradeType + "\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
