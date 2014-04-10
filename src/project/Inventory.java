

package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class Inventory implements java.io.Serializable {
    
    public static ArrayList<Item> inventory = new ArrayList<>();
    
    
    
    
    
    public static void createItem(Item i) {
        
        inventory.add(i);
     
    }
     public static void createTestItemz(){
       
         Item iphone4s = new Item("iPhone","ID123","4S",100.00, 200.00, 1);
         inventory.add(iphone4s);
         
         Item iphone5 = new Item("iPhone","ID4545","5",150.00, 200.00, 2);
         inventory.add(iphone5);
         
         Item htcone = new Item("HTC","ID7474","One",110.00, 210.00, 3);
         inventory.add(htcone);

     }
    
    
    
    
    public static void save() {
//        try {
//            FileOutputStream fileOut = new FileOutputStream("inventory.ser", true);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(inventory);
//            out.close();
//            fileOut.close();
//            System.out.printf("Serialized data is saved in inventory.ser");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
        
              try{
         FileOutputStream fos= new FileOutputStream("myfile");
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(inventory);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }


    }
    public static void load()
    {
        ArrayList<Item> e = new ArrayList<>();
        try {           
            FileInputStream fileIn = new FileInputStream("inventory.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.printf("Serialized data loaded");
            e = (ArrayList<Item>) in.readObject();
            
            
           //Collections.copy(inventory,e);
            inventory = new ArrayList<>(e);
            
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
        
        
        for(Item i : inventory){
            
        System.out.println(i.name);
        }
//        System.out.println("Deserialized Inventory...");
//        //System.out.println("Name: " + e.);
//        System.out.println("ID: " + e.ID());
//        System.out.println("Type: " + e.type());
//        System.out.println("Invoice Price: " + e.invoicePrice());
//        System.out.println("Selling Price: " + e.sellingPrice());
//        System.out.println("Quantity: " + e.quantity());
    }
    
     public static void loadz(){
            try
        {
            FileInputStream fis = new FileInputStream("myfile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            inventory = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
             return;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
          }
}

}
