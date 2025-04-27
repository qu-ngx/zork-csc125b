import java.util.ArrayList;
import java.util.HashMap;

public class Location {
  private String name;
  private String description;
  private ArrayList<Item> items;
  private HashMap<String, Location> connections;

  public Location(String dName, String dDescription) {
    name = dName;
    description = dDescription;
    items = new ArrayList<Item>();
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

  public void connect(String directionName, Location locationObject) {
    connections.put(directionName, locationObject);
  }

  public boolean canMove(String directionName) {
    return connections.containsKey(directionName);
  }

  public Location getLocation(String directionName) {
    return connections.get(directionName);
  }

}
