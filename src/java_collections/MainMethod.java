package java_collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainMethod {
    private static Map<String,HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();
    public static void main(String[] args) {

        // Create Heavenly Bodies
        HeavenlyBody mercury = new HeavenlyBody("Mercury",88);
        HeavenlyBody venus = new HeavenlyBody("Venus",225);
        HeavenlyBody earth = new HeavenlyBody("Earth",365);
        HeavenlyBody mars = new HeavenlyBody("Mars",687);
        HeavenlyBody jupiter = new HeavenlyBody("Jupiter",4332);
        HeavenlyBody saturn = new HeavenlyBody("Saturn",10759);
        HeavenlyBody uranus = new HeavenlyBody("Uranus",30660);
        HeavenlyBody neptune = new HeavenlyBody("Neptune",165);
        HeavenlyBody pluto = new HeavenlyBody("Pluto",248);


        // Add HeavenlyBodies to the solarSystem Map
        solarSystem.put(mercury.getName(), mercury);
        solarSystem.put(venus.getName(), venus);
        solarSystem.put(earth.getName(), earth);
        solarSystem.put(mars.getName(), mars);
        solarSystem.put(jupiter.getName(), jupiter);
        solarSystem.put(saturn.getName(), saturn);
        solarSystem.put(uranus.getName(), uranus);
        solarSystem.put(neptune.getName(), neptune);
        solarSystem.put(pluto.getName(), pluto);


        // Add 9 planets to planets HashSet
        planets.add(mercury);
        planets.add(venus);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(saturn);
        planets.add(uranus);
        planets.add(neptune);
        planets.add(pluto);


        // Add HeavenlyBodies - Moons
        HeavenlyBody earthMoon = new HeavenlyBody("Moon",27);
        HeavenlyBody deimosMoon = new HeavenlyBody("Deimos",1.3);
        HeavenlyBody callistoMoon = new HeavenlyBody("Callisto",16.7);
        HeavenlyBody ganymedeMoon = new HeavenlyBody("Ganymede",7.1);
        HeavenlyBody europaMoon = new HeavenlyBody("Europa",3.5);
        HeavenlyBody iOMoon = new HeavenlyBody("Io",1.8);
        HeavenlyBody phobosMoon = new HeavenlyBody("Phobos",0.3);


        // Add Moons to the Solar System
        solarSystem.put(earthMoon.getName(), earthMoon);
        solarSystem.put(deimosMoon.getName(), deimosMoon);
        solarSystem.put(callistoMoon.getName(), callistoMoon);
        solarSystem.put(ganymedeMoon.getName(), ganymedeMoon);
        solarSystem.put(europaMoon.getName(), europaMoon);
        solarSystem.put(iOMoon.getName(), iOMoon);
        solarSystem.put(phobosMoon.getName(), phobosMoon);

        // Add moons specific to Mars
        mars.addMoon(deimosMoon);
        mars.addMoon(phobosMoon);

        // Add moons specific to Jupiter
        jupiter.addMoon(callistoMoon); // temp is still Jupiter
        jupiter.addMoon(ganymedeMoon); // temp is still Jupiter
        jupiter.addMoon(europaMoon); // temp is still Jupiter
        jupiter.addMoon(iOMoon); // temp is still Jupiter



        // Print planets
        System.out.println("Planets\n"+
                "==================");
        for (HeavenlyBody planet : planets){
            System.out.println("\t" +planet.getName());
        }

        HeavenlyBody body = solarSystem.get("Jupiter");
        System.out.println("Moons of "+ body.getName());
        for (HeavenlyBody jupiterMoon : body.getSatellites()){
            System.out.println("\t" +jupiterMoon.getName());
        }




















    }
}
