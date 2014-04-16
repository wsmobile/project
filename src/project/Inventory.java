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
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static project.ShoppingCart.shoppingCart;




public class Inventory implements java.io.Serializable {

    public static ArrayList<Item> inventory = new ArrayList<>();

    
    
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

    public static void setInvetoryItemQuantity() {

        int k = 0;
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

    public static void removeInvetoryItem(String name) {

        for (int i = 0; i < inventory.size(); i++) {

            if (inventory.get(i).getName().equals(name)) {

                inventory.remove(i);

            }
        }
    }

    public static void createItem(Item i) {

        if (inventory.equals(i)) {
            JOptionPane.showMessageDialog(new JFrame(), "name exists!");
        } else {
            inventory.add(i);
        }

    }

    //testing create
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

    public static void save() {

        try {
            FileOutputStream fos = new FileOutputStream("WSMobileUserData\\" + "myfile");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(inventory);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream("WSMobileUserData\\" + "myfile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            inventory = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public static void displayProfits() {

// Profit = Revenues - Costs, 
// Revenues = Sum of sell price for all sold items
// Costs = Sum of invoice price for all items brought in the inventory (bought)
//I did cost  = sum of invoice price for all items sold, otherwise it will be negative for a while
//makes no sence to calculate
        double revenue = 0.0;
        double costs = 0.0;
        File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {//open and read revenue and costs
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("WSMobileUserData\\" + "profits.txt"));
                revenue = Double.valueOf(br.readLine());
                costs = Double.valueOf(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        double profit = revenue - costs;
        String sProfit = Double.toString(profit);
        String sRevenue = Double.toString(revenue);
        String sCosts = Double.toString(costs);

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

    public static void addtoProfits(double inv, double sel, int quant) {
        double revenue = 0.0;
        double costs = 0.0;
        File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {//open and read revenue and costs
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("WSMobileUserData\\" + "profits.txt"));
                revenue = Double.valueOf(br.readLine()); 
                costs = Double.valueOf(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        revenue = revenue + (sel * quant);
        costs = costs + (inv * quant);

        //create and initialize profit file
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("WSMobileUserData\\" + "profits.txt"));

            out.write(Double.toString(revenue));
            out.newLine();
            out.write(Double.toString(costs));
           
            out.close();
        } catch (IOException ee) {
            ee.printStackTrace();
        }

    }

    public static int findQuantity(String name, ArrayList<Item> list){
        
        int q = 0;
        
        for (Item i : list){
            
            if (i.getName().equals(name)){
                q=i.getQuantity();
            }
        }
        
        return q;
    }
    
    public static void clearProfit()
    {
         File f = new File("WSMobileUserData\\" + "profits.txt");
        if (f.exists()) {//open and read revenue and costs

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("WSMobileUserData\\" + "profits.txt"));

                out.write(Double.toString(0));
                out.newLine();
                out.write(Double.toString(0));

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    }
    
}
