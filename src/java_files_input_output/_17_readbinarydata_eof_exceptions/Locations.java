package java_files_input_output._17_readbinarydata_eof_exceptions;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/*
 * Reading Binary Data and End Of File Exceptions
 *
 *
 *
 */

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) {

      try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
          for (Location location : locations.values() ) {
              locFile.writeInt(location.getLocationId());
              locFile.writeUTF(location.getDescription());
              System.out.println("Writing location "+location.getLocationId()+ " : "+location.getDescription());
              System.out.println("Writing "+ (location.getExits().size() - 1) + " exits.");
              locFile.writeInt(location.getExits().size()-1);
              for (String direction : location.getExits().keySet()){
                  if (!direction.equalsIgnoreCase("Q")){
                      System.out.println("\t\t"+ direction + ","+location.getExits().get(direction));
                      locFile.writeUTF(direction);
                      locFile.writeInt(location.getExits().get(direction));
                  }
              }
          }
      }catch (IOException e){
          e.printStackTrace();
      }

    }

    static {
        System.out.println("======================== Loading.... static initialization block =================");

        try(DataInputStream locFile  = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            boolean eof = false;

            while (!eof){
                try{
                    Map<String,Integer> exits = new LinkedHashMap<>();
                    int locId = locFile.readInt();
                    String description = locFile.readUTF();
                    int numExits = locFile.readInt();
                    System.out.println("Read Location "+locId+ " : "+description);
                    System.out.println("Found "+numExits+ " exits");
                    for (int i = 0; i < numExits; i++) {
                        String direction = locFile.readUTF();
                        int destination = locFile.readInt();
                        exits.put(direction,destination);
                        System.out.println("\t\t"+direction+ ","+destination);
                    }
                    locations.put(locId , new Location(locId,description,exits));
                }catch (EOFException e){
                    eof = true;
                }
            }
        }catch (IOException e){
            System.out.println("IO Exception");
        }
        System.out.println("======================== static initialization block Loaded.. =================\n\n");
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}