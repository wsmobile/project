/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author freestyler
 */
public class Item implements java.io.Serializable{
    // ID, type, quantity, invoice price, and selling price
    public String ID;
    public String name;
    public double invoicePrice;
    public double sellingPrice;
    public String type;
    public int quantity ;
    
    ImageIcon picture = new ImageIcon();
    
    
    public Item()
    {
        
    }
    
    //constructors
    public Item (String n){
        name = n;
    }
    
    public Item (String n, String id, String t, double i, double s, int q)
    {
        ID = id;
        name = n;
        invoicePrice = i;
        sellingPrice = s;
        type = t;
        quantity = q;
        
    }
     
    //accessor methods
    public String ID()
    {
        return ID;
    }
    
    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public double invoicePrice() {
        return invoicePrice;
    }

    public double sellingPrice() {
        return sellingPrice;
    }

    public int quantity() {
        return quantity;
    }
    
    public ImageIcon getpicture(){
        return picture;
    }

   
}
