package com.towerdefense.game;

/**
 * Represents an enemy in the game
 */
public class Enemy {
    private double x;
    private double y;
    private double health;
    private double maxHealth;
    private double speed;
    private String type;
    private boolean frozen;
    private int freezeTimer;
    
    public Enemy(double x, double y, double health, double speed, String type) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.type = type;
        this.frozen = false;
        this.freezeTimer = 0;
    }
    
    public void update() {
        if (frozen) {
            freezeTimer--;
            if (freezeTimer <= 0) {
                frozen = false;
            }
        } else {
            // Move enemy towards tower
            x -= speed;
        }
    }
    
    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    public void freeze(int duration) {
        frozen = true;
        freezeTimer = duration;
    }
    
    public boolean isDead() {
        return health <= 0;
    }
    
    public boolean hasReachedTower() {
        return x <= 100;
    }
    
    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getHealth() { return health; }
    public double getMaxHealth() { return maxHealth; }
    public double getSpeed() { return speed; }
    public String getType() { return type; }
    public boolean isFrozen() { return frozen; }
    
    // Setters
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
