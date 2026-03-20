package com.towerdefense.decorator;

import com.towerdefense.model.Tower;

/**
 * Decorator that adds a protective shield to the tower
 * Provides defensive capability to block incoming attacks
 */
public class ShieldDecorator extends TowerDecorator {
    
    public ShieldDecorator(Tower tower) {
        super(tower);
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + " + Energy Shield";
    }
    
    @Override
    public String getSpecialPower() {
        String currentPower = super.getSpecialPower();
        if (currentPower.equals("None")) {
            return "Block Attacks";
        }
        return currentPower + " | Block Attacks";
    }
}
