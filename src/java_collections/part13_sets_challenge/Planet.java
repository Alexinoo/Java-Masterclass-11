package java_collections.part13_sets_challenge;

/*
 * Planet subclass - extends HeavenlyBody class
 *
 * Constructor
 *  - We'll change and instead of accepting bodyType as a parameter on the Planet constructor, we'll just accept the name and the orbital period
 *      - Then we'll pass the enum constant for Planets to the parent constructor
 *
 * Methods
 *  - Override addSatellite(HeavenlyBody moon) from the Parent class
 *      - Make sure we only add heavenly body of type moons to the Satellite
 *      - We're performing the addition by calling the super which is the addSatellite() from the HeavenlyBody class
 *      - But again we're checking to see whether this is a moon , because in terms of satellites a moon is valid for planet
 */

public class Planet extends HeavenlyBody{
    public Planet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.PLANET);
    }

    @Override
    public boolean addSatellite(HeavenlyBody heavenlyBody) {
        if (heavenlyBody.getKey().getBodyType() == BodyTypes.MOON)
            return super.addSatellite(heavenlyBody);
        return false;
    }
}
