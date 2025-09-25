import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuApp 
{
    public static void main(String[] args) 
    {
        List<MenuItem> menu = new ArrayList<>();

        // TODO: Add some menu items
        menu.add(new MenuItem("Burger", 5.99));
        menu.add(new MenuItem("Pizza", 8.99));
        menu.add(new MenuItem("Sushi", 12.99));

        MenuService menuService = new MenuService(menu);
        CartService cartService = new CartService();

        Scanner scan = new Scanner(System.in);

        // TODO: Display menu to the user
        menuService.displayMenu();

        // TODO: Assignment 1 - Ask user to select an item by name and display the price

        System.out.println("Would you like to look at something specific on the menu? (yes/no)");
        String userResponse = scan.nextLine();
        while (!userResponse.equalsIgnoreCase("yes") && !userResponse.equalsIgnoreCase("no")) 
        {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            userResponse = scan.nextLine();
        }
        if (userResponse.equalsIgnoreCase("yes"))
        {
            while (true) 
            {
                System.out.print("Enter the name of the item you want to select: ");
                String selectedItemName = scan.nextLine();
                MenuItem temp = menuService.findItem(selectedItemName);

                if (temp != null) 
                {
                    System.out.println(temp.getName() + " costs $" + temp.getPrice());
                } 
                else
                {
                    System.out.println("Item not found. Please try again.");
                }
                System.out.println("Would you like to look at another item? (yes/no)");
                userResponse = scan.nextLine();
                if (userResponse.equalsIgnoreCase("no")) 
                {
                    break; 
                }
            }
            
        }

        // TODO: Assignment 2 - Let user add selected items to a "cart" (a separate list)

        List<CartItem> cart = new ArrayList<>();
        System.out.println("Would you like to add items to your cart? (yes/no)");
        userResponse = scan.nextLine();
        while (!userResponse.equalsIgnoreCase("yes") && !userResponse.equalsIgnoreCase("no")) 
        {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            userResponse = scan.nextLine();
        }
        if (userResponse.equalsIgnoreCase("no")) 
        {
            System.out.println("Thank you for visiting! Goodbye.");
            scan.close();
            return;
        }   
        while (true) 
        {
            System.out.print("Enter the name of the item to add to your cart (or type 'done' to finish): ");
            String itemToAdd = scan.nextLine();
            if (itemToAdd.equalsIgnoreCase("done")) 
            {
                break;
            }
            MenuItem menuItem = menuService.findItem(itemToAdd);
            if (menuItem != null) 
            {
                System.out.println(menuItem.getName() + " costs $" + menuItem.getPrice());
                System.out.print("Enter quantity: ");
                int quantity = 0;

                while (true) 
                {
                    System.out.print("Enter quantity: ");
                    String input = scan.nextLine();
                    try 
                    {
                        quantity = Integer.parseInt(input);
                        if (quantity <= 0) 
                        {
                            System.out.println("Quantity must be at least 1. Please try again.");
                        } 
                        else 
                        {
                            break; // valid input, exit loop
                        }
                    } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Invalid number. Please enter a valid quantity.");
                }
            }
                cartService.addItem(menuItem, quantity);
                System.out.println(quantity + "x " + menuItem.getName() + " added to your cart.");
            } 
            else 
            {
                System.out.println("Item not found. Please try again.");
            }
        }

        // TODO: Assignment 3 - Calculate the total cost of items in the cart

        cartService.displayCart();
        System.out.println("Thank you for your order!");

        scan.close();
    }
}