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
            for (Item j : shoppingCart) {
                if (j.getName().equals(n)) {
                    j.setQuantity(2);
                } 
                else {
                    if (i.getName().equals(n)) {
                        Item newItem = new Item(i.getpicture(),i.getName(), i.getID(), i.getType(), 
                                i.getInvoicePrice(), i.getSellingPrice(), 1, i.getfilename());
                        shoppingCart.add(newItem);
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
    
}
