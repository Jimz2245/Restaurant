import java.awt.Menu;
import java.util.List;

public class MenuService 
{
    private List<MenuItem> menu;

    public MenuService(List<MenuItem> menu) 
    {
        this.menu = menu;
    }

    public void displayMenu() 
    {
        System.out.println("Menu:");
        for (MenuItem item : menu) 
        {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    public MenuItem findItem(String itemName) 
    {
        for (MenuItem item : menu) 
        {
            if (item.getName().equalsIgnoreCase(itemName)) 
            {
                return item;
            }
        }
        return null; // Item not found
    }

    public void addItemToMenu(MenuItem newItem) 
    {
        menu.add(newItem);
    }
}