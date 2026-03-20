package com.towerdefense.model;

/**
 * Base implementation of a Tower
 * This is the core component that will be decorated
 */
public class BaseTower implements Tower {
    
    @Override
    public String getDescription() {
        return "Base Tower";
    }

    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public int getSpeed() {
        return 1; // 1 shot per second
    }

    @Override
    public String getSpecialPower() {
        return "None";
    }
}
