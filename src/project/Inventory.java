package project;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

    public static void setInvetoryItemQuantity(int q, String name) {

        int k=0;
        int j = 0;
        for (Item i : inventory) {
            j++;

            if (i.getName().equals(name)) {
                j--;
                k=i.getQuantity();
                break;
            }

        }
        k-=q;
        inventory.get(j).setQuantity(k);

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
        
        Image myimage = ImageIO.read(new File("iphone4.jpg"));
        ImageIcon icon = new ImageIcon(myimage);
        
        Image myimage1 = ImageIO.read(new File("iphone5.jpg"));
        ImageIcon icon1 = new ImageIcon(myimage1);
        



        Item iphone4s = new Item(icon,"iPhone4", "ID123", "apple", 100.00, 200.00, 1);
        inventory.add(iphone4s);

        Item iphone5 = new Item(icon1,"iPhone5", "ID4545", "apple", 150.00, 200.00, 2);
        inventory.add(iphone5);

        Item htcone = new Item("HTC", "ID7474", "One", 110.00, 210.00, 3);
        inventory.add(htcone);

    }

    public static void save() {

        try {
            FileOutputStream fos = new FileOutputStream("myfile");
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
            FileInputStream fis = new FileInputStream("myfile");
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

 

}
