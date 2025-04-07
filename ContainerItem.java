
// Create a Java class named ContainerItem that is a subclass of Item
import java.util.ArrayList;

public class ContainerItem extends Item {
  // Add the following fields to your ContainerItem class:
  // o An ArrayList named items that stores Item objects added to it
  private ArrayList<Item> items;

  // Add a constructor that takes three parameters as inputs:
  // (a) the ContainerItem’s name,
  // (b) the ContainerItem’s type,
  // and (c) the ContainerItem’s description.
  // The ContainerItem’s ArrayList should be initialized to empty
  // (i.e., no items).
  public ContainerItem(String cName, String cType, String cDescription) {
    super(cName, cType, cDescription);
    items = new ArrayList<Item>();
  }

  // Add a method named addItem that takes an Item object as a parameter
  // (i.e., the item to be added to this ContainerItem)
  public void addItem(Item pItem) {
    items.add(pItem);
  }

  // Add a method named hasItem that takes a String (i.e., an Item’s name) as a
  // parameter. This method should return true if the ContainerItem’s ArrayList
  // contains an item with the same name, otherwise, it should return false
  public boolean hasItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }
    return false;
  }

  // Add a method named removeItem that takes a String
  // (i.e., an Item’s name) as a parameter.
  // This method should remove the Item from the ContainerItem’s ArrayList
  // and it should also return the Item object back when it is finished
  public Item removeItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        items.remove(item);
        return item;
      }
    }
    return null;
  }

  // Override the toString( ) method from the Item class so that for
  // ContainerItems, it returns the
  // ContainerItem’s
  // (a) name, (b) type, (c) description, and also (d) a listing of the Items’
  // names - only that it contains (note: the example ContainerItem’s fields are
  // shown in green while the names of Items it
  // contains are shown in blue).
  // Toolbox [ Container ] : an old rusted toolbox with drawers that contains:
  // + Wrench
  // + Soda
  // + Screwdriver

  @Override
  public String toString() {
    StringBuilder containerInfo = new StringBuilder();
    containerInfo.append(getName()).append(" [ ").append(getType()).append(" ] : ").append(getDescription())
        .append("\n");

    if (items.isEmpty()) {
      containerInfo.append("No items in inventory.\n");
    } else {
      for (Item item : items) {
        containerInfo.append("+ ").append(item.getName()).append("\n");
      }
    }

    return containerInfo.toString();
  }

}
