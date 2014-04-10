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
    public String getID()
    {
        return ID;
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public ImageIcon getpicture(){
        return picture;
    }
    
    public void setID(String id)
    {
         ID=id;
    }
    
    public void setName(String n) {
         name=n;
    }

    public void setType(String t) {
         type=t;
    }

    public void setInvoicePrice(double i) {
         invoicePrice=i;
    }

    public void setSellingPrice(double s) {
         sellingPrice=s;
    }

    public void setQuantity(int q) {
         quantity=q;
    }
    
//    public void setPicture(){
//         picture;
//    }
   
}
