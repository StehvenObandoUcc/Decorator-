package com.towerdefense.server;

import com.towerdefense.game.GameEngine;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/**
 * Main game server that handles HTTP requests and manages the game engine
 * Uses embedded HTTP server to serve API endpoints and frontend
 */
public class GameServer {
    
    private static GameEngine gameEngine;
    
    public static void main(String[] args) throws Exception {
        // Initialize game engine
        gameEngine = new GameEngine();
        
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Frontend endpoint - serves the HTML interface
        server.createContext("/", new FrontendHandler());
        
        // API endpoints for tower management
        server.createContext("/api/tower/status", new StatusHandler(gameEngine));
        server.createContext("/api/tower/upgrade/fast", new UpgradeHandler("fast", gameEngine));
        server.createContext("/api/tower/upgrade/ice", new UpgradeHandler("ice", gameEngine));
        server.createContext("/api/tower/upgrade/shield", new UpgradeHandler("shield", gameEngine));
        server.createContext("/api/tower/upgrade/gold", new UpgradeHandler("gold", gameEngine));
        server.createContext("/api/tower/reset", new ResetHandler(gameEngine));
        
        // Game state endpoint
        server.createContext("/api/game/state", new GameStateHandler(gameEngine));
        
        // Game restart endpoint
        server.createContext("/api/game/restart", new RestartGameHandler(gameEngine));
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   Tower Defense Game Server - Decorator Pattern Demo  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✓ Server started successfully on port 8080");
        System.out.println("✓ Game engine initialized and running");
        System.out.println("✓ Open your browser and navigate to:");
        System.out.println("  → http://localhost:8080");
        System.out.println();
        System.out.println("Press Ctrl+C to stop the server");
    }
}
