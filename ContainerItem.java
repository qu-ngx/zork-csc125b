
import java.util.ArrayList;

public class ContainerItem extends Item {
  private ArrayList<Item> items;

  public ContainerItem(String cName, String cType, String cDescription) {
    super(cName, cType, cDescription);
    items = new ArrayList<Item>();
  }

  public void addItem(Item pItem) {
    items.add(pItem);
  }

  public boolean hasItem(String itemName) {
    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(itemName)) {
        return true;
      }
    }
    return false;
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
      containerInfo.delete(containerInfo.length() - 1, containerInfo.length());
    }
    return containerInfo.toString();
  }
}
