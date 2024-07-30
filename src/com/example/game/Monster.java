package com.example.game;

import java.util.ArrayList;
import java.util.List;

/*
 * Monster class
 *
 * - Create a Monster class and persist the data relating to it in the same way
 * - Monsters are very similar to the players but they don't have weapons, just their natural claws and fangs
 *
 * - By creating Monster class, we can still use these other methods and create some code that actually saves the state of the monsters
 *
 * Fields
 *  name : String
 *  hitPoints : int
 *  strength : int
 *
 * Constructor
 *  Monster(String name , int hitPoints , int strength)
 *      - Initialize all the variables
 *
 * Getters
 *  getName() : String
 *  getHitPoints() : int
 *  getStrength() : int
 *
 * toString()
 *  - print all the properties
 *
 * - Implement read() and write() from ISaveable
 *
 *  write() : ArrayList<String>
 *      - Create an empty arrayList "values"
 *      - read the values from the Monster's object and store in the values arrayList
 *      - return the ArrayList
 *
 *  read(List<String> savedValues) : void
 *      - read the values passed as a parameter to this method (ideally from the console)
 *      - update to Monster's object
 *
 *
 */
public class Monster implements ISaveable {

    private String name ;
    private int hitPoints , strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    @Override
    public List<String> write() {
        ArrayList<String> values = new ArrayList<>();
        values.add(0 , this.name);
        values.add(1 , String.valueOf(this.hitPoints));
        values.add(2 , String.valueOf(this.strength));
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && !savedValues.isEmpty()){
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
        }
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }
}
