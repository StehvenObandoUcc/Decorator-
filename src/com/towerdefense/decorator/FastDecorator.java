package com.towerdefense.decorator;

import com.towerdefense.model.Tower;

/**
 * Decorator that increases the tower's firing speed
 * Adds rapid fire capability to the tower
 */
public class FastDecorator extends TowerDecorator {
    
    private static final int SPEED_BOOST = 5;
    
    public FastDecorator(Tower tower) {
        super(tower);
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + " + Rapid Fire";
    }
    
    @Override
    public int getSpeed() {
        return super.getSpeed() + SPEED_BOOST;
    }
}
