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
    
    public static void addShoppingCart(String n)
    {
        for(Item i : inventory)
        {
            if(i.getName().equals(n))
            {
                Item newItem = new Item(i.getName(),"",i.getType(),0,i.getSellingPrice(),1);
                shoppingCart.add(newItem);
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
            total+=i.getSellingPrice();
        }
        return total;
    }
    
}
