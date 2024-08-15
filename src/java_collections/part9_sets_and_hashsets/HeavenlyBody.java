package java_collections.part9_sets_and_hashsets;

import java.util.HashSet;
import java.util.Set;
/*
 * HeavenlyBody class
 *  - Make it a final class
 *
 * Fields
 *  final name : String
 *  final double  : orbitalPeriod
 *  final satellites : Set<HeavenlyBody>
 *
 * Constructor
 *  HeavenlyBody(String name , double orbitalPeriod)
 *      - Initialize name and orbital period
 *      - Initialize satellites to a new HashSet
 *
 * Getter
 *  getName() : String
 *
 *  getOrbitalPeriod() : double
 *
 *  getSatellites : Set<HeavenlyBody>
        - instead of returning the actual object, return a shallow copy of satellites
 *      - makes the code that is calling this obj, doesn't have access to our original HashSet
 *
 * Methods
 *  addMoon(HeavenlyBody moon) : boolean
 *      - we want to pass the parameter which will be a HeavenlyBody, which we'll call moon and we'll add it to the
 *        HashSet
 *      - returns true if we succeeded, otherwise false
 *
 */

public final class HeavenlyBody {

    private final String name;
    private final double orbitalPeriod;

    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addMoon(HeavenlyBody moon){
        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }
}
