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
    public static double total;
    
    public static void addShoppingCart(String n) {
              
            for (Item i : inventory) {
                if (i.getName().equals(n)) {
                if (shoppingCart.isEmpty()) {

                        Item newItem = new Item(i.getName(), i.getID(), i.getType(),
                                i.getInvoicePrice(), i.getSellingPrice(), 1);
                        shoppingCart.add(newItem);
                        
                    } else {
                        for (Item j : shoppingCart) {
                            if (j.getName().equals(n)) {
                                setShoppingCartItem(n,j.getQuantity());
                            } else {

                                Item newItem = new Item(i.getName(), i.getID(), i.getType(),
                                        i.getInvoicePrice(), i.getSellingPrice(), 1);
                                shoppingCart.add(newItem);

                            }
                        }
                    }
            }
        }
    }
    
    public static void clearShoppingCart()
    {
           shoppingCart.clear();
    }
    
    public static double total()
    {
        for(Item i: shoppingCart)
        {
            total+=i.getSellingPrice()*i.getQuantity();
        }
        return total;
    }
    
    public static void setShoppingCartItem( String n, int q) {

        int j = 0;
        for (Item i : shoppingCart) {
            j++;

            if (i.getName().equals(n)) {
                j--;
                break;
            }

        }
        shoppingCart.get(j).setQuantity(q+1);

    }

}
