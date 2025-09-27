import javax.swing.*;
import java.awt.Color;

public class MenuAppGUI {
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Foodie App");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 
        frame.setVisible(true);

        JLabel menuLabel = new JLabel("🍽️ Menu");
        menuLabel.setBounds(50, 20, 100, 20);
        frame.add(menuLabel);

        JLabel cartLabel = new JLabel("🛒 Your Cart");
        cartLabel.setBounds(50, 200, 100, 20);
        frame.add(cartLabel);

        String[] menuItems = {"🍔 Burger - $5.99", "🍕 Pizza - $8.99", "🍣 Sushi - $12.99"};
        JList<String> menuList = new JList<>(menuItems);
        menuList.setBounds(50, 50, 300, 100);
        frame.add(menuList);

        DefaultListModel<String> cartModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartModel);
        cartList.setBounds(50, 220, 300, 150);
        frame.add(cartList);

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        menuList.setBackground(Color.WHITE);
        cartList.setBackground(Color.WHITE);

        JButton addButton = new JButton("Add to Cart");
        JTextField qtyField = new JTextField("1");
        qtyField.setBounds(50, 170, 50, 30);
        frame.add(qtyField);
        addButton.setBounds(150, 170, 120, 30);
        frame.add(addButton);

        addButton.addActionListener(e -> {
            int selectedIndex = menuList.getSelectedIndex();
            if (selectedIndex != -1) 
            {
                String selectedItem = menuItems[selectedIndex];
                int qty = Integer.parseInt(qtyField.getText());
                for (int i = 0; i < qty; i++) 
                {
                    cartModel.addElement(selectedItem);
                }
                JOptionPane.showMessageDialog(frame, qty + selectedItem + " added to your cart!");
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Please select an item first.");
            }
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(150, 400, 100, 30);
        frame.add(checkoutButton);

        checkoutButton.addActionListener(e -> {
            if (cartModel.isEmpty()) 
            {
                JOptionPane.showMessageDialog(frame, "Your cart is empty!");
                return;
            }

            double subtotal = 0;
            for (int i = 0; i < cartModel.size(); i++) 
            {
                String cartItem = cartModel.get(i);
                String[] parts = cartItem.split("\\$");
                double price = Double.parseDouble(parts[1]);
                subtotal += price;
            }

            // Tip selection
            String[] options = {"15%", "18%", "20%", "Custom"};
            int tipChoice = JOptionPane.showOptionDialog(
                    frame,
                    "Select a tip percentage:",
                    "Tip Selection",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            double tipPercent = 0;
            switch(tipChoice) 
            {
                case 0: tipPercent = 0.15; break;
                case 1: tipPercent = 0.18; break;
                case 2: tipPercent = 0.20; break;
                case 3:
                    String customTipStr = JOptionPane.showInputDialog(frame, "Enter tip percentage (e.g., 22 for 22%):");
                    try 
                    {
                        tipPercent = Double.parseDouble(customTipStr) / 100.0;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Using 0% tip.");
                        tipPercent = 0;
                    }
                    break;
                default: tipPercent = 0;
            }

            double totalWithTip = subtotal * (1 + 0.08875 + tipPercent);

            StringBuilder receipt = new StringBuilder("Your Cart:\n");
            for (int i = 0; i < cartModel.size(); i++) 
            {
                receipt.append(cartModel.get(i)).append("\n");
            }
            receipt.append("\nSubtotal: $").append(String.format("%.2f", subtotal));
            receipt.append("\nTax (8.875%): $").append(String.format("%.2f", subtotal * 0.08875));
            receipt.append("\nTip: $").append(String.format("%.2f", subtotal * tipPercent));
            receipt.append("\nTotal: $").append(String.format("%.2f", totalWithTip));

            JOptionPane.showMessageDialog(frame, receipt.toString());
        });

    }
}
