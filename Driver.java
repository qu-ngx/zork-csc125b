import java.util.Scanner;

public class Driver {
  public static Location currLocation;

  // Create a static ContainerItem variable in your Driver class
  // named myInventory that you wrote.
  public static ContainerItem myInventory;

  // Create a static method named createWorld in your Driver class. This method
  // should:
  public static void createWorld() {
    //  Construct four new Location objects for your game (e.g., kitchen, hallway,
    // bedroom, etc.)
    Location kitchen = new Location("Kitchen", "A luring kitchen");
    Location hallway = new Location("Hallway", "Long line awaits");
    Location bedroom = new Location("Bedroom", "Sleepy?? ZZZZZ.");
    Location livingRoom = new Location("Living Room", "A place with a cozy fireplace?");

    // Set the currLocation static variable to store the address of one of your four
    // locations – this will be
    // where the user’s character will start in your world when the game begins.
    currLocation = kitchen;

    // [Hallway] -- [Living Room]
    // . | ........... |
    // [Bedroom] -- [Kitchen*]

    // outdegrees: hallway(2), bathroom(2), kitchen(2), bedroom(2)

    // Connect the Location objects together with one another using the connect( )
    // method to form a world “graph”
    kitchen.connect("west", bedroom);
    bedroom.connect("east", kitchen);
    kitchen.connect("north", livingRoom);
    livingRoom.connect("south", kitchen);
    bedroom.connect("north", hallway);
    hallway.connect("south", bedroom);
    livingRoom.connect("west", hallway);
    hallway.connect("east", livingRoom);

    // Add Items to your Locations so that you can thoroughly test that your
    // commands are working correctly. (at least 4 not all in )
    Item knife = new Item("Knife", "Tool", "Dismantle and Cleave, with a huge red stain on it, likely blood");
    Item turkey = new Item("Turkey", "Food", "Some leftover turkey, smells terrible, likely rotten");
    Item lamp = new Item("Lamb", "Decoration", "A broken old lamb");
    Item candle = new Item("Candle", "Decoration", "A half-burnt candle");
    Item pillow = new Item("Pillow", "Decoration", "A dusty pillow");

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
    // In the main( ) method, call the createWorld( ) method before entering the
    // “command processing loop”
    // to construct the world and setup the game before the user starts playing
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
          // If the user types look, your game should only print Items that are found at
          // that location currently
          // (i.e., the look command should work correctly in any location of your world
          // as the character moves around)
          case "look":
            System.out.println(currLocation.getName() + " - " + currLocation.getDescription());
            currLocation.listAllItems();
            break;
          // If the user types examine __NAME__,
          // your game should still correctly allow the user to examine an
          // Item found in their current location
          case "examine":
            if (words.length < 2) {
              System.out.println("You did not tell me an item that you want to examine");
            } else {
              String itemName = words[1];
              Item foundItem = currLocation.getItem(itemName);
              if (foundItem != null) {
                System.out.println(foundItem.toString());
              } else {
                System.out.println(itemName + " does not exist in the location");
              }
            }
            break;
          // If the user types go _DIRECTION_ , your program should move the character’s
          // current location _DIRECTION_ that the user typed
          // (if it exists and is a legal move)
          case "go":
            if (words.length > 1) {
              String direction = words[1];
              if (direction != null && currLocation.canMove(direction)) {
                currLocation = currLocation.getLocation(direction);
              } else if (!direction.equals("west") &&
                  !direction.equals("east") &&
                  !direction.equals("south") &&
                  !direction.equals("north")) {
                // What if the user types ‘go’ and a direction name
                // that is not north/east/south/west/etc.?
                System.out.println("Is the direction East, West, North, or South?");
              } else {
                // The ‘go’ command should notify the user if an invalid direction name is used
                // backward)
                // What if the user types ‘go’ and a valid direction name,
                // however, there is no location connected in that direction?
                System.out.println("Correct direction. However, you cannot go that way");
              }
            } else {
              // What if the user just types ‘go’ with no direction name?
              System.out.println("Please specify the direction you want to go");
            }
            break;
          case "inventory":
            System.out.println(myInventory.toString());
            break;
          // If the user types take __NAME__, your program should try to find the matching
          // item at
          // the character’s current location. If a matching item is found, the item
          // should be removed
          // from the current location and added to the character’s inventory (e.g., take
          // _SWORD_ ).
          // If a matching item is not found, your program should print “Cannot find that
          // item here”.
          case "take":
            if (words.length > 1) {
              String itemName = words[1];
              Item foundItem = currLocation.getItem(itemName);
              if (foundItem != null) {
                myInventory.addItem(foundItem);
                currLocation.removeItem(itemName);
              } else {
                // The ‘take’ command should notify the user that the item does not exist when
                // an invalid Item name is typed (i.e., if I type ‘take shoe’ and there is not a
                // shoe item at my character’s current location)
                System.out.println("Cannot find that item here");
              }
            } else {
              System.out.println("Please specify the item you want to take");
            }
            break;
          // If the user types drop __NAME__, your program should try to find the matching
          // item in
          // the character’s inventory. If a matching item is found, the item should be
          // removed from
          // the character’s inventory and added to the current location’s items (e.g.,
          // drop _HAT_ ). If a matching item is not found, your program should print
          // “Cannot find that item in your
          // inventory.”
          case "drop":
            if (words.length > 1) {
              String itemName = words[1];
              if (myInventory.hasItem(itemName) == true) {
                Item droppedItem = myInventory.removeItem(itemName);
                currLocation.addItem(droppedItem);
              } else {
                // The ‘drop’ command should notify the user that the item does not exist when
                // an invalid Item name is typed (i.e., if I type ‘drop shoe’
                // and there is not a shoe item in my character’s inventory)
                System.out.println("Cannot find that item in your inventory");
              }
            } else {
              // The ‘drop’ command should notify the user that they need to type an item’s
              // name if it was omitted and (b) it should not cause the program to crash
              System.out.println("Please specify the item you want to drop");
            }
            break;
          // If the user types help, then your program should print a list of all the
          // commands currently supported with a brief one-sentence description for what
          // they do to help a new player understand your game. You should write a helper
          // method to print these command Descriptions.
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
