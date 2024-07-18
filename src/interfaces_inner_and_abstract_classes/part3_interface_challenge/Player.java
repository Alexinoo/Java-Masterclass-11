package interfaces_inner_and_abstract_classes.part3_interface_challenge;

import java.util.ArrayList;
import java.util.List;

/*
 * Player class
 *
 * - Fields
 *      name , weapon : String
 *      hitPoints , strength : int
 *
 * - Constructor
 *      Player(String name, int hitPoints , int strength)
 *          - Initialize the 3 member variables
 *          - Initialize weapon to "Sword"
 *
 * - Getters
 *      getName() : String
 *      getWeapon() : String
 *      getHitPoints() : int
 *      getStrength() : int
 *
 * - Setters
 *      setName(String name) : void
 *      setWeapon(String weapon) : void
 *      setHitPoints(int hitPoints) : void
 *      setStrength(int strength) : void
 *
 * - Implement write()
 *      - Create an ArrayList of String
 *      - read the values from the Player's object
 *      - store to List ArrayLists
 *      - return List<String>

 * - Implement read(List<String> values)
 *      - read the values from the console and set it to the Player object
 */

public class Player implements ISaveable{
    private String name , weapon;
    private int hitPoints , strength;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "Sword";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }

    @Override
    public List<String> write() {
        List<String> values = new ArrayList<>();
        values.add(0,this.name);
        values.add(1, String.valueOf(this.hitPoints));
        values.add(2, String.valueOf(this.strength));
        values.add(3, String.valueOf(this.weapon));
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && !savedValues.isEmpty()){
            this.name = savedValues.get(0);
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
            this.weapon = savedValues.get(3);
        }
    }
}
