import java.util.ArrayList;
import java.util.List;


public class CartService 
{
    private List<CartItem> cart =   new ArrayList<>();

    public void addItem(MenuItem menuItem, int quantity) 
    {
        for(CartItem cartItem : cart) 
        {
            if (cartItem.getItem().getName().equalsIgnoreCase(menuItem.getName())) 
            {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new CartItem(menuItem, quantity));
    }

    public void displayCart() 
    {
        if (cart.isEmpty()) 
        {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Your Cart:");
        double total = 0;
        for (CartItem cartItem : cart) 
        {
            System.out.println(cartItem);
            total += cartItem.getSubtotal();
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
