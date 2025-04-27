import java.util.Scanner;

public class Driver {
  public static Location currLocation;

  public static ContainerItem myInventory;

  public static void createWorld() {
    Location kitchen = new Location("Kitchen", "A luring kitchen");
    Location hallway = new Location("Hallway", "Long line awaits");
    Location bedroom = new Location("Bedroom", "Sleepy?? ZZZZZ.");
    Location livingRoom = new Location("Living Room", "A place with a cozy fireplace?");

    currLocation = kitchen;

    kitchen.connect("west", bedroom);
    bedroom.connect("east", kitchen);
    kitchen.connect("north", livingRoom);
    livingRoom.connect("south", kitchen);
    bedroom.connect("north", hallway);
    hallway.connect("south", bedroom);
    livingRoom.connect("west", hallway);
    hallway.connect("east", livingRoom);

    Item knife = new Item("Knife", "Tool", "Dismantle and Cleave, with a huge red stain on it, likely blood");
    Item turkey = new Item("Turkey", "Food", "Some leftover turkey, smells terrible, likely rotten");
    Item lamp = new Item("Lamp", "Decoration", "A broken old lamp");
    Item candle = new Item("Candle", "Decoration", "A half-burnt candle");
    Item pillow = new Item("Pillow", "Decoration", "A dusty pillow");

    // 1.1 Modify your createWorld( ) method in your Driver class to add at least
    // three different ContainerItems (e.g., chest, desk, vault, etc.) spread out
    // across
    // your world’s locations so that I may test your code.
    ContainerItem desk = new ContainerItem("Desk", "Decoration", "This desk seems messy");
    desk.addItem(new Item("Paper", "Misc", "Is this Chad's exam?"));
    desk.addItem(new Item("Pencil", "Tool", "This pencil seems blunt"));

    ContainerItem chest = new ContainerItem("Chest", "Tool", "A thing that store another thing");
    chest.addItem(new Item("Coin", "Treasure", "A shiny gold coin."));
    chest.addItem(new Item("Map", "Tool", "A tattered map of the surrounding area."));

    ContainerItem vault = new ContainerItem("Vault", "Tool", "A safe that store valuable things");
    vault.addItem(new Item("Diamond", "Gem", "A thing that can make you rich."));
    vault.addItem(new Item("Key", "Tool", "A thing that lead to another thing."));

    kitchen.addItem(desk);
    bedroom.addItem(chest);
    hallway.addItem(vault);

    kitchen.addItem(knife);
    kitchen.addItem(turkey);
    bedroom.addItem(lamp);
    hallway.addItem(candle);
    livingRoom.addItem(pillow);
  }

  public static void printHelp() {
    System.out.println("look - Display location's name, description and item available there");
    System.out.println("examine - Show item's types and description");
    System.out.println("inventory - Inspect your inventory");
    System.out.println("go - Go the direction that leads to a new place");
    System.out.println("take - Add an item that is available in your current location to your inventory");
    System.out.println("drop - Drop an item from you inventory");
    System.out.println("quit - Exit the game");
  }

  public static void main(String[] args) {
    myInventory = new ContainerItem("Inventory", "Container",
        "This is your inventory, which you can store whatever you want");

    createWorld();

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter command: ");

      String processedInput = scanner.nextLine().trim().toLowerCase();

      String[] words = processedInput.split(" ");

      if (words.length > 0) {
        String command = words[0];
        switch (command) {
          case "quit":
            scanner.close();
            return;
          case "look":
            System.out.println(currLocation.getName() + " - " + currLocation.getDescription());
            currLocation.listAllItems();
            break;
          case "examine":
            if (words.length == 2) {
              String itemName = words[1];
              Item foundItem = currLocation.getItem(itemName);
              // If the user types examine __CONTAINER__ , then your program should print the
              // ContainerItem’s name, type, description, and names-only of the items that it
              // contains
              if (foundItem != null && foundItem instanceof ContainerItem) {
                ContainerItem foundContainer = (ContainerItem) foundItem;
                System.out.println(foundContainer.toString());
              } else if (foundItem != null) {
                System.out.println(foundItem.toString());
              } else {
                System.out.println(itemName + " does not exist in the location");
              }
            } else {
              System.out.println("You did not tell me an item that you want to examine");
            }
            break;
          case "go":
            if (words.length > 1) {
              String direction = words[1];
              if (direction != null && currLocation.canMove(direction)) {
                currLocation = currLocation.getLocation(direction);
              } else if (!direction.equalsIgnoreCase("west") &&
                  !direction.equalsIgnoreCase("east") &&
                  !direction.equalsIgnoreCase("south") &&
                  !direction.equalsIgnoreCase("north")) {
                System.out.println("Is the direction East, West, North, or South?");
              } else {
                System.out.println("Correct direction. However, there's no location in that direction?");
              }
            } else {
              System.out.println("Please specify the direction you want to go");
            }
            break;
          case "inventory":
            System.out.println(myInventory.toString());
            break;
          case "take":
            if (words.length == 2) {
              String itemName = words[1];
              Item foundItem = currLocation.getItem(itemName);
              if (foundItem != null) {
                myInventory.addItem(foundItem);
                currLocation.removeItem(itemName);
              } else {
                System.out.println("Cannot find that item here");
              }
            } else if (words.length == 4 && words[2].equals("from")) {
              // If the user types take__NAME__ from __CONTAINER__ , then your program should
              // remove the Item whose name is __NAME__ from the ContainerItem whose name is
              // __CONTAINER__ at the current location and add the item to the character’s
              // inventory (e.g., take key from chest)
              String itemName = words[1];
              String containerName = words[3];
              Item containerItem = currLocation.getItem(containerName);

              if (containerItem instanceof ContainerItem) {
                ContainerItem container = (ContainerItem) containerItem;
                if (container != null) {
                  if (container.hasItem(itemName) != false) {
                    Item item = container.removeItem(itemName);
                    myInventory.addItem(item);

                  } else {
                    // The ‘take ___ from ____’ command should notify the user if a valid
                    // ContainerItem name was provided however the Item name provided was not
                    // contained inside
                    System.out.println("Correct container item. However, cannot find that item here");
                  }
                }
              } else {
                // The ‘take ___ from ____’ command should notify the user if an invalid
                // ContainerItem name was provided (i.e., a ContainerItem that does not exist or
                // an Item that is not a ContainerItem)
                System.out.println("Cannot find that containerItem here");
              }
            } else {
              System.out.println("Invalid command! Is itemName omitted or invalid itemName?");
            }
            break;
          case "put":
            // If the user types put __NAME__ in __CONTAINER__ , then your program should
            // remove the Item whose name is __NAME__ from the character’s inventory and add
            // the item to the ContainerItem whose name is __CONTAINER__ at the current
            // location (e.g., put sword in vault)

            if (words.length == 4 && words[2].equals("in")) {
              String itemName = words[1];
              String containerName = words[3];
              Item containerItem = currLocation.getItem(containerName);

              if (containerItem instanceof ContainerItem) {
                ContainerItem container = (ContainerItem) containerItem;
                if (container != null) {
                  if (myInventory.hasItem(itemName) != false) {
                    Item item = myInventory.removeItem(itemName);
                    container.addItem(item);
                  } else {
                    // The ‘put ___ in ____’ command should notify the user if a valid ContainerItem
                    // name was provided however the Item name provided was not in your character’s
                    // inventory
                    System.out.println("Correct containerItem. However, provided item isn't in your inventory");
                  }
                }
              } else {
                System.out.println("Cannot find that container item here");
              }
            } else {
              System.out.println("Not a valid put command. Try again!");
            }
            break;
          case "drop":
            if (words.length > 1) {
              String itemName = words[1];
              if (myInventory.hasItem(itemName) == true) {
                Item droppedItem = myInventory.removeItem(itemName);
                currLocation.addItem(droppedItem);
              } else {
                System.out.println("Cannot find that item in your inventory");
              }
            } else {
              System.out.println("Please specify the item you want to drop");
            }
            break;
          case "help":
            printHelp();
            break;
          default:
            System.out.println("I don’t know how to do that");
            break;
        }
      }
    }

  }
}
