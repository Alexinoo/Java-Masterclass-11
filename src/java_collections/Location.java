package java_collections;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private final int locationId;
    private final String description;

    private final Map<String,Integer> exits;

    public Location(int locationId, String description) {
        this.locationId = locationId;
        this.description = description;
        this.exits = new HashMap<>();
        this.exits.put("Q",0);
    }

    public void addExits(String direction , int location){
        this.exits.put(direction,location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(this.exits);
    }
}
