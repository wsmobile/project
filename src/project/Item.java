

/*
IF YOU CHANGE SOMETHING IN THIS CLASS SERILIZATION WILL NOT LOAD
 */

package project;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author wsmobile
 */
public class Item implements java.io.Serializable{
    // ID, type, quantity, invoice price, and selling price
    private String ID;
    private String name;
    private double invoicePrice;
    private double sellingPrice;
    private String type;
    private int quantity ;
    private ImageIcon picture ;
    private String filename;
    
    
    public Item()
    {
        
    }
    
    //constructors
    public Item (String n){
        name = n;
    }
    
    //constructor no picture
        public Item ( String n, String id, String t, double i, double s, int q)
    {
        
        ID = id;
        name = n;
        invoicePrice = i;
        sellingPrice = s;
        type = t;
        quantity = q;
        
    }
    //overloaded w picture
    public Item (ImageIcon p, String n, String id, String t, double i, double s, int q)
    {
        picture = p;
        ID = id;
        name = n;
        invoicePrice = i;
        sellingPrice = s;
        type = t;
        quantity = q;
        
    }
    
    
        //overloaded w picture and filename
    public Item (ImageIcon p, String n, String id, String t, double i, double s, int q, String f)
    {
        picture = p;
        ID = id;
        name = n;
        invoicePrice = i;
        sellingPrice = s;
        type = t;
        quantity = q;
        filename = f;
        
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
    
    public String getfilename(){
        return filename;
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
    
    public void setPicture(ImageIcon p){
        picture = p;
    }
    
    public void setfilename(String f){
        filename = f;
    }
   
}
