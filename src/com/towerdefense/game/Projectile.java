package com.towerdefense.game;

/**
 * Represents a projectile fired by the tower
 */
public class Projectile {
    private double x;
    private double y;
    private double targetX;
    private double targetY;
    private double damage;
    private double speed;
    private boolean active;
    private Enemy target;
    
    public Projectile(double x, double y, Enemy target, double damage) {
        this.x = x;
        this.y = y;
        this.target = target;
        this.targetX = target.getX();
        this.targetY = target.getY();
        this.damage = damage;
        this.speed = 8.0;
        this.active = true;
    }
    
    public void update() {
        if (!active) return;
        
        // Update target position if enemy is still alive
        if (target != null && !target.isDead()) {
            targetX = target.getX();
            targetY = target.getY();
        }
        
        // Calculate direction
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        // Check if projectile reached target
        if (distance < speed) {
            if (target != null && !target.isDead()) {
                target.takeDamage(damage);
            }
            active = false;
        } else {
            // Move towards target
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }
    }
    
    public boolean isActive() {
        return active;
    }
    
    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getDamage() { return damage; }
    public Enemy getTarget() { return target; }
}
