package java_collections.part10_equals_and_hashcode_methods;

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

    @Override
    public boolean equals(Object obj){
        if (this == obj)     // is it the same object ,does the object equal to itself , if so, return true
            return true;
        System.out.println("obj.getClass() is "+obj.getClass());
        System.out.println("this.getClass() is "+this.getClass());

        if ((obj == null) || (obj.getClass() != this.getClass())) // do a test to see that if obj is equal to null
            return false;
        String objName = ((HeavenlyBody) obj).getName();
        return this.name.equals(objName);

    }

    @Override
    public int hashCode() {
        System.out.println("hashCode() called");
        return this.name.hashCode() + 57;
    }
}
