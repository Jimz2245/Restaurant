import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a menu item
public class MenuItem 
{
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() 
    { 
        return name; 
    }
    public double getPrice() 
    { 
        return price; 
    }

    @Override
    public String toString() 
    {
        return name + " - $" + price;
    }
}

