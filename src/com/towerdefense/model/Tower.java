package com.towerdefense.model;

/**
 * Main interface for the Decorator Pattern
 * Defines the contract for all tower types and decorators
 */
public interface Tower {
    /**
     * Gets the description of the tower including all applied decorators
     * @return String description of the tower
     */
    String getDescription();
    
    /**
     * Gets the total damage of the tower
     * @return Damage value
     */
    int getDamage();
    
    /**
     * Gets the attack speed (shots per second)
     * @return Speed value
     */
    int getSpeed();
    
    /**
     * Gets all special powers applied to the tower
     * @return String describing special abilities
     */
    String getSpecialPower();
}
