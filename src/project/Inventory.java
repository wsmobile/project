package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static project.ShoppingCart.shoppingCart;



/**
 * this is a inventory class that serves as a controller
 * it stores a list of items and provides functions to 
 * modify the inventory
 * 
 * @author sraychev
 */
public class Inventory implements java.io.Serializable {

    //holds a list of all items in inventory
    public static ArrayList<Item> inventory = new ArrayList<>();

    
    /**
     * mutator, sets the invoice price, selling price and quantity for an item in inventory
     * @param inv represents the new inventory price that is being passed
     * @param s represents the new selling price that is being passed
     * @param q represents the new quantity that is being passed
     * @param name the name of the item
     * preconditions: the item with this name exist in inventory
     * postconditions: the item was updated with a new selling price, invoice price and quantity
     *                  and inventory is saved
     */
    public static void setInvetoryItem(double inv, double s, int q, String name) {

        int j = 0;
        for (Item i : inventory) {
            j++;

            if (i.getName().equals(name)) {
                j--;
                break;
            }

        }
        inventory.get(j).setInvoicePrice(inv);
        inventory.get(j).setSellingPrice(s);
        inventory.get(j).setQuantity(q);

    }

    /**
     * mutator, sets the quantity of an item in inventory
     * It takes no parameters, it is called when a customer makes a purchase.
     * It takes the number of items purchased from the shopping cart and subtracts it
     * from the inventory, thus updating and saving the inventory every time a customer 
     * makes a purchase.
     * preconditions: the item exist in inventory and shopping cart
     * postconditions: the quantity of all items from the shopping cart was subtracted from inventory
     */
    public static void setInvetoryItemQuantity() {

        int k ;
         for (Item i : inventory) {

            for (Item l : shoppingCart) {

                if (i.getName().equals(l.getName())) {
                 
                    k = i.getQuantity() - l.getQuantity();
                    setInvetoryItem(i.getInvoicePrice(), i.getSellingPrice(), k, i.getName());
                    
                }
            }
        }
        save();
    }

    /**
     * mutator, it removes an item from the inventory completely 
     * @param name is the name of the item that is being removed
     * preconditions: the item exists in inventory
     * postconditions: the item was deleted from inventory
     */
    public static void removeInvetoryItem(String name) {

        for (int i = 0; i < inventory.size(); i++) {

            if (inventory.get(i).getName().equals(name)) {

                inventory.remove(i);

            }
        }
    }

    /**
     * mutator, it adds a new item to the inventory
     * @param i is the item that is being added
     * preconditions: user has logged in owner mode and clicked on the create item button
     * postconditions: item was added to the inventory
     */
    public static void createItem(Item i) {

        if (inventory.equals(i)) {
            JOptionPane.showMessageDialog(new JFrame(), "name exists!");
        } else {
            inventory.add(i);
        }

    }

    /**
     * mutator, this function is for testing purposes
     * It invokes different constructors of item and initializes an inventory
     * if the application is to be run for a first time
     * @throws IOException 
     */
    public static void createTestItemz() throws IOException {

        Image myimage = ImageIO.read(new File("WSMobileUserData\\" + "iphone4.jpg"));
        ImageIcon icon = new ImageIcon(myimage);

        Image myimage1 = ImageIO.read(new File("WSMobileUserData\\" + "iphone5.jpg"));
        ImageIcon icon1 = new ImageIcon(myimage1);

        Item iphone4s = new Item(icon, "iPhone4", "ID123", "apple", 100.00, 200.00, 1);
        inventory.add(iphone4s);

        Item iphone5 = new Item(icon1, "iPhone5", "ID4545", "apple", 150.00, 200.00, 2);
        inventory.add(iphone5);

        Item htcone = new Item("HTC", "ID7474", "One", 110.00, 210.00, 3);
        inventory.add(htcone);

    }

    /**
     * mutator, it saves any changes of the items in inventory to a file using serialization
     * It takes no parameters.
     * Needs to be called every time when a change is made.
     */
    public static void save() {

        try {
            try (FileOutputStream fos = new FileOutputStream("WSMobileUserData\\" + "myfile"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(inventory);
            }
        } catch (IOException ioe) {
        }

    }

    /**
     * accessor, it reads all items from a file and adds them in to a array list
     * It needs to be called every time when a view is called
     */
    public static void load() {
        try {
            try (FileInputStream fis = new FileInputStream("WSMobileUserData\\" + "myfile"); ObjectInputStream ois = new ObjectInputStream(fis)) {
                inventory = (ArrayList) ois.readObject();
            }
        } catch (IOException ioe) {
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
    }

    /**
     * accessor, it displays the sales profits up to this point in a JFrame
     * It reads a file that contains the revenue and costs up to this point
     * and calculates profit.
     * 
     */
    public static void displayProfits() {

// Profit = Revenues - Costs, 
// Revenues = Sum of sell price for all sold items
// Costs = Sum of invoice price for all items brought in the inventory (bought)
//I did cost  = sum of invoice price for all items sold, otherwise it will be negative for a while
//makes no sence to calculate
        double revenue = 0.0;
        double costs = 0.0;
        File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {
            //open and read revenue and costs
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("WSMobileUserData\\" + "profits.txt"));
                revenue = Double.valueOf(br.readLine());
                costs = Double.valueOf(br.readLine());
            } catch (IOException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
        double profit = revenue - costs;
        String sProfit = Double.toString(profit);
        String sRevenue = Double.toString(revenue);
        String sCosts = Double.toString(costs);

        //create a frame and dsplay totals
        final JFrame totals = new JFrame();
        totals.setVisible(true);
        totals.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        totals.setLayout(new GridLayout(4, 1));
        totals.add(new JLabel("Total Profit: " + sProfit+" $"));
        totals.add(new JLabel("Revenues: " + sRevenue+" $"));
        totals.add(new JLabel("Costs: " + sCosts+" $"));

        JButton ok = new JButton("OK");
        totals.add(ok, BorderLayout.SOUTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                totals.dispose();

            }
        });
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        totals.setLocation(x - 600, y - 300);
        totals.setSize(200, 200);
        totals.repaint();

    }

    /**
     * mutator, it ads to profits every time a sale was made.
     * @param inv is the invoice price of the item that was sold
     * @param sel is the selling price of the item that was sold
     * @param quant is the number of items that were sold
     * preconditions: none
     * postconditions: the values of revenue and costs were written to a file
     */
    public static void addtoProfits(double inv, double sel, int quant) {
        double revenue = 0.0;
        double costs = 0.0;
        
        File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {
            //open and read revenue and costs
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("WSMobileUserData\\" + "profits.txt"));
                revenue = Double.valueOf(br.readLine()); 
                costs = Double.valueOf(br.readLine());
            } catch (IOException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                }
            }
        }

        revenue = revenue + (sel * quant);
        costs = costs + (inv * quant);

        //create and initialize profit file if one does not exist
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter("WSMobileUserData\\" + "profits.txt"))) {
                out.write(Double.toString(revenue));
                out.newLine();
                out.write(Double.toString(costs));
            }
        } catch (IOException ee) {
        }

    }

    /**
     * accessor, it returns the quantity of an item from a list of items
     * @param name is the name of the item that is being inquired
     * @param list is a list of items to be searched
     * @return the quantity of an item
     */
    public static int findQuantity(String name, ArrayList<Item> list){
        
        int q = 0;
        
        for (Item i : list){
            
            if (i.getName().equals(name)){
                q=i.getQuantity();
            }
        }
        
        return q;
    }
    
    /**
     * mutator, resets the profits to 0
     */
    public static void clearProfit()
    {
         File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {//open and read revenue and costs

            try {
                try (BufferedWriter out = new BufferedWriter(new FileWriter("WSMobileUserData\\" + "profits.txt"))) {
                    out.write(Double.toString(0));
                    out.newLine();
                    out.write(Double.toString(0));
                }
            } catch (IOException e) {
            }
        }
    
    }
    
}
