public class Item {
  private String name;
  private String type;
  private String description;

  public Item(String dName, String dType, String dDescription) {
    name = dName;
    type = dType;
    description = dDescription;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String nName) {
    name = nName;
  }

  public void setType(String nType) {
    type = nType;
  }

  public void setDescription(String nDescription) {
    description = nDescription;
  }

  public String toString() {
    return name + " [ " + type + " ] : " + description;
  }
}
