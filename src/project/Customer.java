package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import static project.Inventory.inventory;
import static project.ShoppingCart.shoppingCart;

/**
 * This is a customer class that serve as a view which will displays all items
 * in inventory and allows the customer to add item to shopping cart or remove
 * all items that inside the shopping cart, and it also allow check out the
 * shopping cart which will also update the inventory quantities and profit.
 *
 * @author weijie
 */
public class Customer extends JFrame implements ActionListener {

    public Container con;

    //creating boxes in which we will display different components
    final Box middle = Box.createVerticalBox();
    final Box buy = Box.createVerticalBox();
    final Box choz = Box.createVerticalBox();

    //creating variables that we need to change and display dinamically
    private static final JLabel currentpic = new JLabel("");
    private static final JLabel currentquantity = new JLabel("");
    private static final JLabel currentname = new JLabel(" ");
    private static final JLabel currenttype = new JLabel(" ");
    private static final JLabel curentprice = new JLabel(" ");
    public static final JLabel cartItems = new JLabel(" ");

    private static String currentName = "";

    /**
     * Constructor for this class
     */
    public Customer() {
        /**
         * Read in the inventory to extract items
         */
        Inventory.load();
        //open window
        this.setVisible(true);
        this.setSize(600, 435);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.white);

        /**
         * Creating the "Add to cart" button and its action listener This action
         * listener will check the item quantity inside the inventory and then
         * add item to the shopping cart.
         */
        JButton Add = new JButton(" Add to cart ");
        Add.setMaximumSize(new Dimension(120, 25));
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add to cart function here  

                if (Inventory.findQuantity(currentName, inventory)
                        > Inventory.findQuantity(currentName, shoppingCart)) {
                    //allow adding
                    ShoppingCart.addShoppingCart(currentName);
                    displayShoppingCartItem();
                } else {
                    //display error msg
                    JOptionPane.showMessageDialog(null, "Sorry, we dont have that many items in stock!");
                    displayShoppingCartItem();
                }
            }
        });

        /**
         * Creating the "Clear cart" button and its action listener This action
         * listener will clear all shopping cart items.
         */
        JButton Clear = new JButton(" Clear cart ");
        Clear.setMaximumSize(new Dimension(120, 25));
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clear shopping cart items
                ShoppingCart.clearShoppingCart();
                //refrash the shopping cart
                displayShoppingCartItem();
            }
        });

        /**
         * Creating the "Checkout" button and its action listener This action
         * listener will open a new window that confirm this current purchase.
         */
        JButton Checkout = new JButton(" Checkout ");
        Checkout.setMaximumSize(new Dimension(120, 25));
        Checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!shoppingCart.isEmpty()) {
                    //new window open
                    final JFrame confirmationpage = new JFrame("Order Summary");
                    //get middle of the screen for positioning
                    int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
                    int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
                    confirmationpage.setSize(500, 500);
                    confirmationpage.setLocation(x - 300, y - 300);
                    confirmationpage.setVisible(true);
                    confirmationpage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    confirmationpage.setLayout(new GridLayout(10, 1));

                    //display all items that inside the shopping cart
                    JPanel P1 = new JPanel(new GridLayout(0, 3));
                    P1.add(new JLabel("Model:"));
                    P1.add(new JLabel("Quantity:"));
                    P1.add(new JLabel("Price:"));
                    confirmationpage.add(P1);

                    for (Item s : shoppingCart) {
                        JPanel Pn = new JPanel(new GridLayout(0, 3));
                        Pn.add(new JLabel(s.getName()));
                        Pn.add(new JLabel("" + s.getQuantity()));
                        Pn.add(new JLabel("" + s.getSellingPrice() * s.getQuantity()));
                        Pn.setBackground(Color.white);
                        confirmationpage.add(Pn);
                    }

                    /**
                     * Creating the "confirm" button and its action listener
                     * This action listener will update both the item quantities
                     * and the profit of this purchase, and then it will close
                     * all open window.
                     */
                    JButton confirm = new JButton("CONFIRM");
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //update profit
                            for (Item s : shoppingCart) {
                                Inventory.addtoProfits(s.getInvoicePrice(), s.getSellingPrice(), s.getQuantity());

                            }
                            //update quantities
                            Inventory.setInvetoryItemQuantity();
                            JOptionPane.showMessageDialog(null, "Thank you for your purchase!");
                            //close all open window
                            confirmationpage.dispose();
                            Customer.super.dispose();

                        }
                    });

                    /**
                     * Creating the "confirm" button and its action listener
                     * This action listener will cancel this purchase and close
                     * the confirm window.
                     */
                    JButton cancel = new JButton("CANCEL");
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //confirm page close
                            confirmationpage.dispose();

                        }
                    });

                    JPanel P8 = new JPanel(new GridLayout(0, 1));
                    P8.add(new JLabel("************************************************************************************************"));
                    confirmationpage.add(P8);
                    P8.setBackground(Color.white);

                    JPanel P9 = new JPanel(new GridLayout(0, 3));
                    P9.add(new JLabel("Grand Total: "));
                    P9.add(new JLabel(" "));
                    P9.add(new JLabel(Double.toString(ShoppingCart.ShoppingCartTotal())));   ///NEED TO PRINT TOTAL for the whole order HERE
                    P9.setBackground(Color.LIGHT_GRAY);
                    confirmationpage.add(P9);

                    JPanel P10 = new JPanel();
                    P10.add(confirm);
                    P10.add(cancel);
                    confirmationpage.add(P10);

                } else {
                    JOptionPane.showMessageDialog(null, "Shopping cart is empty!");
                }

            }
        });
        
        
        /**
         * This is the left top side of the customer window It will display all
         * inventory item separately and each item will display as button.
         */
        //Choose items choz - first box
        for (int i = 0; i < inventory.size(); i++) {
            /**
             * Creating the "butt" button and its action listener This action
             * listener will display the current item's information like
             * picture, name, and price.
             */
            JButton butt = new JButton(inventory.get(i).getName());
            choz.add(Box.createRigidArea(new Dimension(0, 5)));
            choz.add(butt);
            final String name = inventory.get(i).getName();
            butt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //call display function
                    displayItem(name);
                }
            });
        }

        /**
         * This is the right middle side of the customer window It will display
         * all items that inside the shopping cart.
         */
        choz.setBorder(BorderFactory.createTitledBorder("Choose "));
        choz.setBounds(0, 0, 125, 450);
        con.add(choz);

        /// Current item - middle box    
        middle.setVisible(true);
        middle.setBounds(125, 0, 275, 450);
        middle.setBorder(BorderFactory.createTitledBorder(" "));
        middle.add(currentpic);
        middle.add(currenttype);
        middle.add(currentname);
        middle.add(curentprice);
        middle.add(currentquantity);
        con.add(middle);

        //// Buy box - 3rd box      
        buy.setVisible(true);
        buy.setBounds(400, 0, 200, 450);
        buy.setBorder(BorderFactory.createTitledBorder("Selected items"));
        con.add(buy);

        ImageIcon image = new ImageIcon("WSMobilePictures\\1.jpg");
        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);

        buy.add(new JLabel("current itmes in cart:"));
        buy.add(cartItems);
        buy.add(label1);
        buy.add(Add);
        buy.add(Clear);
        buy.add(new JLabel("*********************************"));
        buy.add(Checkout);
        buy.add(new JLabel("*********************************"));
        con.add(buy);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * This is a function that displays the specific item's information to the
     * customer window
     *
     * @param weijie
     */
    private void displayItem(String n) {

        for (Item i : inventory) {

            if (i.getName().equals(n)) {

                Image myimage = null;
                try {
                    myimage = ImageIO.read(new File("WSMobilePictures\\" + i.getfilename()));
                } catch (IOException ioe) {
                }

                Image newimg = myimage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);

                currentpic.setIcon(icon);
                currentpic.repaint();
                currenttype.setText("Type: " + i.getType());
                currentname.setText("Model: " + i.getName());
                currentName = i.getName();
                curentprice.setText("Price: " + String.valueOf(i.getSellingPrice()));
                currentquantity.setText("Quantity: " + i.getQuantity());
                middle.repaint();

                break;
            }
        }

    }

    /**
     * This function will display the shopping cart items and their total tot he
     * customer window
     */
    private void displayShoppingCartItem() {
        String k = "<html><br>";
        if (shoppingCart.isEmpty()) {

//            shoppingCartItemPrice.setText(" ");
        } else {
            for (Item i : shoppingCart) {

                k += "Model: " + i.getName() + "  Qty: " + i.getQuantity() + "<br>";
            }
            k += "<br>Total: " + ShoppingCart.ShoppingCartTotal() + "</html>";
        }
        cartItems.setText(k);
        buy.repaint();

    }
}
