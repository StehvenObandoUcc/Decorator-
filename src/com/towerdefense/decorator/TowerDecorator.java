package com.towerdefense.decorator;

import com.towerdefense.model.Tower;

/**
 * Abstract Decorator for the Tower
 * This class implements the Decorator Pattern by wrapping a Tower object
 * All concrete decorators will extend this class
 */
public abstract class TowerDecorator implements Tower {
    protected Tower decoratedTower;

    public TowerDecorator(Tower tower) {
        this.decoratedTower = tower;
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription();
    }

    @Override
    public int getDamage() {
        return decoratedTower.getDamage();
    }

    @Override
    public int getSpeed() {
        return decoratedTower.getSpeed();
    }

    @Override
    public String getSpecialPower() {
        return decoratedTower.getSpecialPower();
    }
}
