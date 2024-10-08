package java_files_input_output._18_object_io_serialization;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location implements Serializable {
    private final int locationId;
    private final String description;
    private final Map<String,Integer> exits;

    private long serialVersionUID = 1L;
    public Location(int locationId, String description, Map<String,Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        if (exits != null) {
            this.exits = new LinkedHashMap<>(exits);
        }else {
            this.exits = new LinkedHashMap<>();
        }
        this.exits.put("Q",0);
    }

//    public void addExits(String direction , int location){
//        this.exits.put(direction,location);
//    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<>(this.exits);
    }

    protected void addExit(String direction , int location){
        this.exits.put(direction,location);
    }
}
