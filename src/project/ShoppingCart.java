

package project;

import java.util.ArrayList;
import static project.Inventory.inventory;

/**
 * this is a shoppingCart class that serves as a controller
 * it stores a list of items and provides functions to 
 * modify the shopppingCart
 * 
 * @author weijie
 */
public class ShoppingCart {
    
    //holds a list of all items in shoppingCart
    public static ArrayList<Item> shoppingCart = new ArrayList<>();
    public static double total=0;
    
    /**
     * add new item to shoppingCart
     * @param n the name of the item
     * preconditions: the item with this name exist in inventory
     * postconditions: the item with full information will add to shoppingCart list
     */
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
//add item to shoppingCart list
                            shoppingCart.add(new Item(i.getName(), i.getID(), i.getType(),
                                    i.getInvoicePrice(), i.getSellingPrice(), 1));

                        }
            }
        }
    }
    
    /**
     * clear all items inside the shoppingCart
     * preconditions: none
     * postconditions: shoppingCart will be empty
     */
    public static void clearShoppingCart()
    {
        //clear shoppingCart
           shoppingCart.clear();
          
    }
    
    /**
     * calculate the total price of the all items
     * preconditions: none
     * postconditions: total update
     * @return the total of all items selling price
     */
    public static double ShoppingCartTotal()
    {
       total=0;
        for(Item i: shoppingCart)
        {
            //calculate the total of all items' selling prices
            total+=i.getSellingPrice()*i.getQuantity();
        }
        return total;
    }
    
    /**
     * calculate the total price of the all items
     * @param n the name of the item
     * preconditions: the item with this name exist in shoppingCart list
     * postconditions: update the shoppingCart item quantity
     */
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
