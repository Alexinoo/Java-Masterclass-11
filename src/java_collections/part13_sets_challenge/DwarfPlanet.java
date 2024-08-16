package java_collections.part13_sets_challenge;
/*
 * DwarfPlanet subclass - extends HeavenlyBody class
 *
 * Constructor
 *  - Similar to what we did in Planet Constructor
 *
 * Methods
 *  - Won't override the addSatellite()
 *
 */
public class DwarfPlanet extends HeavenlyBody{
    public DwarfPlanet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.DWARF_PLANET);
    }
}
