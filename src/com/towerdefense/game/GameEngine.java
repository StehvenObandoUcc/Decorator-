package com.towerdefense.game;

import com.towerdefense.model.Tower;
import com.towerdefense.model.BaseTower;
import com.towerdefense.decorator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * Main game engine that manages game state and logic
 */
public class GameEngine {
    private Tower currentTower;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    private int gold;
    private int score;
    private int wave;
    private int lives;
    private long lastShot;
    private long lastGoldGen;
    private long lastWaveSpawn;
    private Random random;
    private boolean gameOver;
    
    public GameEngine() {
        init();
        // Start game loop in separate thread
        startGameLoop();
    }
    
    private void init() {
        this.currentTower = new BaseTower();
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.gold = 100;
        this.score = 0;
        this.wave = 1;
        this.lives = 20;
        this.lastShot = System.currentTimeMillis();
        this.lastGoldGen = System.currentTimeMillis();
        this.lastWaveSpawn = System.currentTimeMillis();
        this.random = new Random();
        this.gameOver = false;
    }
    
    public void resetGame() {
        init();
    }
    
    private void startGameLoop() {
        Thread gameThread = new Thread(() -> {
            while (!gameOver) {
                update();
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }
    
    private void update() {
        long currentTime = System.currentTimeMillis();
        
        // Spawn enemies in waves (every 5 seconds)
        if (currentTime - lastWaveSpawn > 5000) {
            spawnWave();
            lastWaveSpawn = currentTime;
        }
        
        // Generate gold if tower has gold decorator
        if (hasGoldDecorator() && currentTime - lastGoldGen > 1000) {
            gold += 10;
            lastGoldGen = currentTime;
        }
        
        // Tower shooting
        long shootDelay = 1000 / currentTower.getSpeed();
        if (currentTime - lastShot > shootDelay && !enemies.isEmpty()) {
            shootAtEnemy();
            lastShot = currentTime;
        }
        
        // Update enemies
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update();
            
            if (enemy.isDead()) {
                score += 10;
                gold += 5;
                enemyIterator.remove();
            } else if (enemy.hasReachedTower()) {
                lives--;
                enemyIterator.remove();
                if (lives <= 0) {
                    gameOver = true;
                }
            }
        }
        
        // Update projectiles
        Iterator<Projectile> projIterator = projectiles.iterator();
        while (projIterator.hasNext()) {
            Projectile proj = projIterator.next();
            proj.update();
            if (!proj.isActive()) {
                projIterator.remove();
            }
        }
        
        // Apply ice effect if tower has ice decorator
        if (hasIceDecorator()) {
            applyIceEffect();
        }
    }
    
    private void spawnWave() {
        int enemyCount = 2 + wave;
        for (int i = 0; i < enemyCount; i++) {
            double y = 100 + random.nextInt(400);
            String type = getRandomEnemyType();
            Enemy enemy = createEnemyByType(type, 800 + i * 100, y);
            enemies.add(enemy);
        }
        wave++;
    }
    
    private String getRandomEnemyType() {
        int rand = random.nextInt(100);
        if (rand < 60) return "basic";
        if (rand < 85) return "fast";
        return "tank";
    }
    
    private Enemy createEnemyByType(String type, double x, double y) {
        switch (type) {
            case "fast":
                return new Enemy(x, y, 15, 0.8, "fast");
            case "tank":
                return new Enemy(x, y, 50, 0.3, "tank");
            default:
                return new Enemy(x, y, 25, 0.5, "basic");
        }
    }
    
    private void shootAtEnemy() {
        if (enemies.isEmpty()) return;
        
        // Find closest enemy
        Enemy target = enemies.get(0);
        for (Enemy enemy : enemies) {
            if (enemy.getX() < target.getX()) {
                target = enemy;
            }
        }
        
        Projectile proj = new Projectile(100, 300, target, currentTower.getDamage());
        projectiles.add(proj);
    }
    
    private void applyIceEffect() {
        for (Enemy enemy : enemies) {
            if (!enemy.isFrozen() && random.nextInt(100) < 5) {
                enemy.freeze(60); // Freeze for 1 second at 60 FPS
            }
        }
    }
    
    private boolean hasGoldDecorator() {
        return currentTower.getSpecialPower().contains("Gold");
    }
    
    private boolean hasIceDecorator() {
        return currentTower.getSpecialPower().contains("Freezes");
    }
    
    public void applyUpgrade(String type) {
        switch (type) {
            case "fast":
                currentTower = new FastDecorator(currentTower);
                break;
            case "ice":
                currentTower = new IceDecorator(currentTower);
                break;
            case "shield":
                currentTower = new ShieldDecorator(currentTower);
                break;
            case "gold":
                currentTower = new GoldDecorator(currentTower);
                break;
        }
    }
    
    public void resetTower() {
        currentTower = new BaseTower();
    }
    
    public String getTowerStatusJson() {
        return String.format(
            "{\"description\":\"%s\",\"damage\":%d,\"speed\":%d,\"power\":\"%s\"}",
            currentTower.getDescription(),
            currentTower.getDamage(),
            currentTower.getSpeed(),
            currentTower.getSpecialPower()
        );
    }
    
    public String getGameStateJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"gold\":").append(gold).append(",");
        json.append("\"score\":").append(score).append(",");
        json.append("\"wave\":").append(wave).append(",");
        json.append("\"lives\":").append(lives).append(",");
        json.append("\"gameOver\":").append(gameOver).append(",");
        
        // Enemies
        json.append("\"enemies\":[");
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            json.append("{\"x\":").append(e.getX())
                .append(",\"y\":").append(e.getY())
                .append(",\"health\":").append(e.getHealth())
                .append(",\"maxHealth\":").append(e.getMaxHealth())
                .append(",\"type\":\"").append(e.getType()).append("\"")
                .append(",\"frozen\":").append(e.isFrozen())
                .append("}");
            if (i < enemies.size() - 1) json.append(",");
        }
        json.append("],");
        
        // Projectiles
        json.append("\"projectiles\":[");
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            json.append("{\"x\":").append(p.getX())
                .append(",\"y\":").append(p.getY())
                .append("}");
            if (i < projectiles.size() - 1) json.append(",");
        }
        json.append("]");
        
        json.append("}");
        return json.toString();
    }
}
