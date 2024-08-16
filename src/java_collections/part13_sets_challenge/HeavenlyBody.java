package java_collections.part13_sets_challenge;

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
 */

public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyTypes { STAR,PLANET,DWARF_PLANET,MOON,COMET,ASTEROID }

    public HeavenlyBody(String name, double orbitalPeriod,BodyTypes bodyType) {
        this.key = new Key(name,bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Key getKey() {
        return key;
    }

    public boolean addSatellite(HeavenlyBody moon){
            return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    @Override
    public final boolean equals(Object obj){
        if (this == obj)     // is it the same object ,does the object equal to itself , if so, return true
            return true;

        if (obj instanceof HeavenlyBody){
            HeavenlyBody theObject = (HeavenlyBody) obj;
            return this.key.equals(theObject.getKey());
        }
        return false;

    }

    @Override
    public final int hashCode() {
        return this.key.hashCode();
    }

    public static Key makeKey(String name, BodyTypes bodyTypes) {
        return new Key(name,bodyTypes);
    }

    @Override
    public String toString() {
        return this.key.name + ": "+ this.key.bodyType + ", "+ this.orbitalPeriod;
    }

    public static final class Key {
        private String name;
        private BodyTypes bodyType;

        private Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            if (this.name.equals(key.getName())){
                return (this.bodyType == key.getBodyType());
            }
            return false;
        }

        @Override
        public String toString() {
            return this.name + ": "+ this.bodyType;
        }
    }
}
