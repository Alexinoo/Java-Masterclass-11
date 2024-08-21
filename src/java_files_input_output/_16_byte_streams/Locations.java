package java_files_input_output._16_byte_streams;

import java.io.*;
import java.util.*;
/*
 * Byte Streams
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
        try( BufferedReader bufferedReader = new BufferedReader(new FileReader("locations_big.txt"))) {
            String line;
            while ( (line = bufferedReader.readLine()) != null){
                String[] data = line.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported loc: "+ loc + ": "+ description);
                Map<String, Integer> tempExit  = new HashMap<>();
                locations.put(loc,new Location(loc,description,tempExit));
            }

        }catch (IOException e){
            e.printStackTrace();
        }


        //////////// Now Read the Exits
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt")))  {
            String input;
            while ( (input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e){
            e.printStackTrace();
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
