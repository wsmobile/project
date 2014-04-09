

package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Inventory implements java.io.Serializable {
    
    public final static ArrayList<Item> inventory = new ArrayList<>();
    
    
    
    
    
    public static void createitem() {
    
    Item iphone = new Item("iphone");
    
    
    inventory.add(iphone);
    
    iphone.name = "IPHONE";
    
    
    
    }
    
    public static void save(){
        
       try
      {
         FileOutputStream fileOut =
         new FileOutputStream("inventory.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(inventory);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/inventory.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
  
    }
}
