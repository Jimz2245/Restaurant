public class CartItem {
    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) 
    {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() 
    { 
        return item; 
    }
    public int getQuantity() 
    { 
        return quantity; 
    }
    public void setQuantity(int quantity) 
    { 
        this.quantity = quantity; 
    }

    public double getSubtotal() 
    {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() 
    {
        return quantity + "x " + item.getName() + " - $" + String.format("%.2f", getSubtotal());
    }
}
