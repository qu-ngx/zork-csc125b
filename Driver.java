import java.util.Scanner;

public class Driver {
  public static Location currLocation;

  public static void main(String[] args) {
    currLocation = new Location("Kitchen",
        "A dark kitchen whose lights are flickering currently has the following items:");

    currLocation.addItem(new Item("Knife", "Tools", "Mighty sharp knife."));
    currLocation.addItem(new Item("Turkey", "Food", "Yummy turkey yikes."));
    currLocation.addItem(new Item("Plate", "Misc", "A clean, white plate."));

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
            if (words.length < 2) {
              System.out.println("You did not tell me  an item that you want to examine");
            } else {
              String itemName = words[1];
              Item foundItem = currLocation.getItem(itemName);
              if (foundItem != null) {
                System.out.println(foundItem.toString());
              } else {
                System.out.println("Cannot find that item.");
              }
            }
            break;
          default:
            System.out.println("I don’t know how to do that");
            break;
        }
      }
    }

  }
}
