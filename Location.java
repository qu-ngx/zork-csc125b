import java.util.ArrayList;
import java.util.HashMap;

public class Location {
  private String name;
  private String description;
  private ArrayList<Item> items;
  // Add a single HashMap member variable to your Location class named connections
  // o This HashMap should associate a direction (e.g., “North”) to a Location
  // object (i.e., memory
  // address). For example, our Kitchen object’s HashMap might store the
  // following:
  private HashMap<String, Location> connections;

  public Location(String dName, String dDescription) {
    name = dName;
    description = dDescription;
    items = new ArrayList<Item>();
    // Modify your Location class’s constructor so that it constructs/initializes
    // the HashMap member variable
    connections = new HashMap<String, Location>();
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String nName) {
    name = nName;
  }

  public void setDescription(String nDescription) {
    description = nDescription;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public boolean hasItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }
    return false;
  }

  public Item getItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return item;
      }
    }
    return null;
  }

  public Item getItem(int index) {
    if (index >= 0 && index < items.size()) {
      return items.get(index);
    }
    return null;
  }

  public int numItems() {
    return items.size();
  }

  public Item removeItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        items.remove(item);
        return item;
      }
    }
    return null;
  }

  public void listAllItems() {
    if (items.isEmpty()) {
      System.out.println("No items here.");
    } else {
      for (Item item : items) {
        System.out.println("+ " + item.getName());
      }
    }
  }

  // Add a method named connect that takes two parameters: (a) a String parameter
  // for the direction’s name and (b) a Location object that we want to connect
  // this Location to. This method should add an entry into the HashMap that
  // associates the direction (example: “North”) to the Location.
  public void connect(String directionName, Location locationObject) {
    connections.put(directionName, locationObject);
  }

  //  Add a method named canMove that takes a String parameter for the direction
  // name. This method should return true if there is a Location object in the
  // HashMap associated with that direction name, otherwise,
  // the method returns false. Be sure to review (a) our video about HashMap and
  // (b) the HashMap API page
  // to familiarize yourself with what method(s) you can call to implement these
  // features efficiently.
  public boolean canMove(String directionName) {
    return connections.containsKey(directionName);
  }

  // Add a method named getLocation that takes a String parameter for the
  // direction name. This method should return the Location object that is
  // associated with the direction, otherwise, it should return null
  public Location getLocation(String directionName) {
    return connections.get(directionName);
  }

}
