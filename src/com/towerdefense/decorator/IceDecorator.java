package com.towerdefense.decorator;

import com.towerdefense.model.Tower;

/**
 * Decorator that adds ice/freeze capability to the tower
 * Allows the tower to slow down or freeze enemies
 */
public class IceDecorator extends TowerDecorator {
    
    public IceDecorator(Tower tower) {
        super(tower);
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + " + Ice Aura";
    }
    
    @Override
    public String getSpecialPower() {
        String currentPower = super.getSpecialPower();
        if (currentPower.equals("None")) {
            return "Freezes Enemies";
        }
        return currentPower + " | Freezes Enemies";
    }
}
