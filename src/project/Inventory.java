

package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Inventory implements java.io.Serializable {
    
    public final static ArrayList<Item> inventory = new ArrayList<>();
    
    
    
    
    
    public static void createItem(Item i) {
        //Item iphone = new Item("iphone");

        inventory.add(i);

        //iphone.name = "IPHONE";
    }
    
    public static void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("inventory.ser", true);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventory);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/inventory.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public static void load()
    {
        ArrayList<Item> e = new ArrayList<>();
        try {           
            FileInputStream fileIn = new FileInputStream("inventory.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (ArrayList<Item>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Inventory class not found");
            c.printStackTrace();
            return;
        }
//        System.out.println("ok");
//        System.out.println("Deserialized Inventory...");
//        System.out.println("Name: " + e.);
//        System.out.println("ID: " + e.ID());
//        System.out.println("Type: " + e.type());
//        System.out.println("Invoice Price: " + e.invoicePrice());
//        System.out.println("Selling Price: " + e.sellingPrice());
//        System.out.println("Quantity: " + e.quantity());
    }
}
