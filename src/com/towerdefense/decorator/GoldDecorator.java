package com.towerdefense.decorator;

import com.towerdefense.model.Tower;

/**
 * Decorator that adds gold generation capability to the tower
 * Allows the tower to passively generate resources
 */
public class GoldDecorator extends TowerDecorator {
    
    private static final int GOLD_PER_SECOND = 10;
    
    public GoldDecorator(Tower tower) {
        super(tower);
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + " + Gold Miner";
    }
    
    @Override
    public String getSpecialPower() {
        String currentPower = super.getSpecialPower();
        String goldPower = "Generates " + GOLD_PER_SECOND + " Gold/sec";
        if (currentPower.equals("None")) {
            return goldPower;
        }
        return currentPower + " | " + goldPower;
    }
}
