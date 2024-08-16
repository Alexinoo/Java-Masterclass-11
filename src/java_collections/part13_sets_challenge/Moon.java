package java_collections.part13_sets_challenge;
/*
 * Moon subclass - extends HeavenlyBody class
 *
 * Constructor
 *  - Similar to what we did in Planet Constructor
 *
 */
public class Moon extends HeavenlyBody{
    public Moon(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.MOON);
    }
}
