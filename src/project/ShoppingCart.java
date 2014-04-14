/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.util.ArrayList;
import static project.Inventory.inventory;

/**
 *
 * @author weijie
 */
public class ShoppingCart {
    
    public static ArrayList<Item> shoppingCart = new ArrayList<>();
    public static double total=0;
    
    public static void addShoppingCart(String n) {
        int k=0;
        
        for (Item i : inventory) {
            
            if (i.getName().equals(n)) {
                
                    for (Item j : shoppingCart) {
                        if(j.getName().equals(n))
                            k=1;                        
                    }
               
                if (k==1) {
                            setShoppingCartItem(n);
                        } else {

                            shoppingCart.add(new Item(i.getName(), i.getID(), i.getType(),
                                    i.getInvoicePrice(), i.getSellingPrice(), 1));

                        }
            }
        }
    }
    
    public static void clearShoppingCart()
    {
        
           shoppingCart.clear();
          
    }
    
    public static double ShoppingCartTotal()
    {
       total=0;
        for(Item i: shoppingCart)
        {
//             System.out.println(i.getSellingPrice());
            total+=i.getSellingPrice()*i.getQuantity();
        }
        return total;
    }
    
    public static void setShoppingCartItem( String n) {

        int j = 0;
        int q = 0;
        for (Item i : shoppingCart) {
            j++;

            if (i.getName().equals(n)) {
                j--;
                q=i.getQuantity()+1;
                break;
            }

        }
        shoppingCart.get(j).setQuantity(q);

    }

}
