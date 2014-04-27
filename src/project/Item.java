

/*
IF YOU CHANGE SOMETHING IN THIS CLASS SERILIZATION WILL NOT LOAD
 */

package project;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This is Item class that serves as a model
 * It's the basement of all other classes, it creates a item object 
 * that allow other classes to use as arrayList type, and it contains
 * many different constructors, mutators, and accessors
 * @author weijie
 */
public class Item implements java.io.Serializable{
    // ID, type, quantity, invoice price, selling price, picture, and filename
    private String ID;
    private String name;
    private double invoicePrice;
    private double sellingPrice;
    private String type;
    private int quantity ;
    private ImageIcon picture ;
    private String filename;
    
    /**
       *Constructor. allow controller to create a new item without any parameter
       * precondition none
       *postcondition a empty item will create
     */
    public Item()
    {
        
    }
    
    /**
     * Constructor. allow controller to create a new item with a name
     * @param n the name of the item
     * preconditions: none
     * postconditions: a item create with a name "n"
     */
    //constructors
    public Item (String n){
        name = n;
    }
    
    /**
     * Constructor. allow controller to create a new item just without picture
     * @param n the name of the item
     * @param id the id of the item
     * @param t the type of the item
     * @param i the invoice pice of the item
     * @param s the selling price of the item
     * @param q the quantity of the item
     * preconditions: none
     * postconditions: a item create without picture
     */
        public Item ( String n, String id, String t, double i, double s, int q)
    {
        
        ID = id;
        name = n;
        invoicePrice = i;
        sellingPrice = s;
        type = t;
        quantity = q;
        
    }
        
    /**
     * Constructor. allow controller to create a new item with picture
     * @param n the name of the item
     * @param id the id of the item
     * @param t the type of the item
     * @param i the invoice pice of the item
     * @param s the selling price of the item
     * @param q the quantity of the item
     * @param p the picture of the item
     * preconditions: none
     * postconditions: a item create with picture but without file name
     */
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
    
    /**
     * Constructor. allow controller to create a new item with picture
     * @param n the name of the item
     * @param id the id of the item
     * @param t the type of the item
     * @param i the invoice pice of the item
     * @param s the selling price of the item
     * @param q the quantity of the item
     * @param p the picture of the item
     * @param f the path of the item picture
     * preconditions: none
     * postconditions: a item create with picture
     */
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
     
    /**
     * Accessor to ID
     * @return ID
     */
    public String getID()
    {
        return ID;
    }
         
    /**
     * Accessor to name
     * @return name
     */
    public String getName() {
        return name;
    }
   
    /**
     * Accessor to type
     * @return type
     */
    public String getType() {
        return type;
    }
   
    /**
     * Accessor to invoice price
     * @return invoice price
     */
    public double getInvoicePrice() {
        return invoicePrice;
    }
   
    /**
     * Accessor to selling price
     * @return selling price
     */
    public double getSellingPrice() {
        return sellingPrice;
    }
   
    /**
     * Accessor to quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
       
    /**
     * Accessor to picture
     * @return picture
     */
    public ImageIcon getpicture(){
        return picture;
    }
       
    /**
     * Accessor to filename
     * @return filename
     */
    public String getfilename(){
        return filename;
    }
    
    /**
     * Mutator to set id 
     * @param id the id of item
     */
    public void setID(String id)
    {
         ID=id;
    }
       
    /**
     * Mutator to set name 
     * @param n the name of item
     */
    public void setName(String n) {
         name=n;
    }
   
    /**
     * Mutator to set type 
     * @param t the type of item
     */
    public void setType(String t) {
         type=t;
    }
   
    /**
     * Mutator to set invoicePrice
     * @param i the invoice price of item
     */
    public void setInvoicePrice(double i) {
         invoicePrice=i;
    }
   
    /**
     * Mutator to set sellingPrice 
     * @param s the sellingPrice of item
     */
    public void setSellingPrice(double s) {
         sellingPrice=s;
    }
   
    /**
     * Mutator to set quantity
     * @param q the quantity of item
     */
    public void setQuantity(int q) {
         quantity=q;
    }
       
    /**
     * Mutator to set picture
     * @param p the picture of item
     */
    public void setPicture(ImageIcon p){
        picture = p;
    }
       
    /**
     * Mutator to set filename 
     * @param f the path of item picture
     */
    public void setfilename(String f){
        filename = f;
    }
   
}
