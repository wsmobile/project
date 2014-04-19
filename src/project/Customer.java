package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.swing.*;
//import static project.FileChooserDemo.finameChosen;
import static project.Inventory.inventory;
import static project.Inventory.setInvetoryItem;
import static project.ShoppingCart.shoppingCart;



public class Customer extends JFrame implements ActionListener {

    public Container con;
   
    final Box middle = Box.createVerticalBox();
    final Box buy = Box.createVerticalBox();
    final Box choz = Box.createVerticalBox();
    
    private static final JLabel currentpic = new JLabel("");
    private static final JLabel currentquantity = new JLabel("");
    private static final JLabel currentname = new JLabel(" ");
    private static final JLabel  currenttype = new JLabel(" ");
    private static final JLabel  curentprice = new JLabel(" ");
    public static final JLabel cartItems = new  JLabel(" ");
    
    private static String currentName = "";
    
    public Customer() {

        Inventory.load();


        this.setVisible(true);
        this.setSize(600, 435);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        con = getContentPane();
        con.setLayout(null);

        con.setBackground(Color.white);


            //Add button
            JButton Add = new JButton(" Add to cart ");     
            Add.setMaximumSize(new Dimension(120, 25));    
            Add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                                                      
                    //add to cart function here  
                               
                   if(Inventory.findQuantity(currentName,inventory) > 
                           Inventory.findQuantity(currentName,shoppingCart)){
                       //allow adding
                       ShoppingCart.addShoppingCart(currentName);  
                       displayShoppingCartItem();
                   }
                   else{
                       //display errormsg
                       JOptionPane.showMessageDialog(null, "Sorry, we dont have that many items in stock!");
                       displayShoppingCartItem();
                   }
 
                }
            });
           
        
        
        
  //Clear button
        JButton Clear = new JButton(" Clear cart ");
        Clear.setMaximumSize(new Dimension(120, 25));
        Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //clear cart function here
                ShoppingCart.clearShoppingCart();
                displayShoppingCartItem();
            }
        });

        
     //Checkout button
        JButton Checkout = new JButton(" Checkout ");
        Checkout.setMaximumSize(new Dimension(120, 25));
        Checkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!shoppingCart.isEmpty()){
                final JFrame confirmationpage = new JFrame("Order Summary");
                
                int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
                int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
                confirmationpage.setSize(500, 500);
                confirmationpage.setLocation(x - 300, y - 300);
             //   confirmationpage.setBackground(Color.white);
                confirmationpage.setVisible(true);
                confirmationpage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                confirmationpage.setLayout(new GridLayout(10,1));
         
                JPanel P1 = new JPanel(new GridLayout(0, 3));
            //   P1.setBackground(Color.gray);

                P1.add(new JLabel("Model:"));
                P1.add(new JLabel("Quantity:"));
                P1.add(new JLabel("Price:"));
                confirmationpage.add(P1);
                
                for (Item s : shoppingCart){
                    
                    JPanel Pn = new JPanel(new GridLayout(0, 3));
                    Pn.add(new JLabel(s.getName()));
                    Pn.add(new JLabel(""+s.getQuantity()));
                    Pn.add(new JLabel(""+s.getSellingPrice()*s.getQuantity()));
                    Pn.setBackground(Color.white);
                    confirmationpage.add(Pn);
                }
                
                JButton confirm= new JButton("CONFIRM");
                confirm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    for (Item s : shoppingCart){
                        Inventory.addtoProfits(s.getInvoicePrice(),s.getSellingPrice(),s.getQuantity());
                        
                    }
                    Inventory.setInvetoryItemQuantity();
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!");
                    confirmationpage.dispose();
                    Customer.super.dispose();
                      

                }
            });
                
                JButton cancel= new JButton("CANCEL");
                cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    confirmationpage.dispose();
            

                }
            });
                
                
                JPanel P8 = new JPanel(new GridLayout(0, 1)); 
                P8.add(new JLabel("************************************************************************************************"));
                confirmationpage.add(P8);
                P8.setBackground(Color.white);
                
                
                JPanel P9 = new JPanel(new GridLayout(0, 3));
                P9.add(new JLabel("Grand Total: "));
                P9.add(new JLabel(" "));
                P9.add(new JLabel(Double.toString(ShoppingCart.ShoppingCartTotal())));   ///NEED TO PRINT TOTAL for the whole order HERE
                P9.setBackground(Color.LIGHT_GRAY);
                confirmationpage.add(P9);
                
                
                  JPanel P10 = new JPanel();
                  P10.add(confirm);
                  P10.add(cancel);
                  confirmationpage.add(P10);
                 
               
                }
                else{
                     JOptionPane.showMessageDialog(null, "Shopping cart is empty!");
                }

            }
        });
        
        
        
        
        //Choose items choz - first box
        for (int i = 0; i < inventory.size(); i++) {
        
            JButton butt = new JButton(inventory.get(i).getName());
            choz.add(Box.createRigidArea(new Dimension(0, 5)));
            choz.add(butt);
            final String name = inventory.get(i).getName();
            butt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                   //call display function
                    displayItem(name);
                }
            });
        }
        choz.setBorder(BorderFactory.createTitledBorder("Choose "));
        choz.setBounds(0, 0, 125, 450);
        con.add(choz);
        
        
        
    /// Current item - middle box    
        
        middle.setVisible(true);
        middle.setBounds(125, 0, 275, 450);
        middle.setBorder(BorderFactory.createTitledBorder(" "));
        middle.add(currentpic);
        middle.add(currenttype);
        middle.add(currentname);
        middle.add(curentprice);
        middle.add(currentquantity);
        con.add(middle);
   
        
    //// Buy box - 3rd box      
        buy.setVisible(true);
        buy.setBounds(400, 0, 200, 450);
        buy.setBorder(BorderFactory.createTitledBorder("Selected items"));
        con.add(buy);

        ImageIcon image = new ImageIcon("WSMobilePictures\\1.jpg");
        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
        
        buy.add(new JLabel("current itmes in cart:"));
        buy.add(cartItems); 
        buy.add(label1);
        buy.add(Add);
        buy.add(Clear);
        buy.add(new JLabel("*********************************"));
        buy.add(Checkout);
        buy.add(new JLabel("*********************************"));
        con.add(buy);

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
   // TODO Auto-generated method stub
    }
    
    
    private void displayItem(String n){
         
        for (Item i : inventory) {
          
            if (i.getName().equals(n)) {
                                
                               
                Image myimage = null ;
                try {
                    myimage = ImageIO.read(new File("WSMobilePictures\\" + i.getfilename()));
                } catch (IOException ioe) {
                }

                Image newimg = myimage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                
                currentpic.setIcon(icon);
                currentpic.repaint();
                currenttype.setText("Type: " + i.getType());
                currentname.setText("Model: " + i.getName());
                currentName=i.getName();
                curentprice.setText("Price: " + String.valueOf(i.getSellingPrice()));                         
                currentquantity.setText("Quantity: " + i.getQuantity());
                middle.repaint();           
            
                break;
            }
    }

}
    
    private void displayShoppingCartItem(){
        String k="<html><br>" ;
        if (shoppingCart.isEmpty()) {
  
//            shoppingCartItemPrice.setText(" ");
            
        } else {
            for (Item i : shoppingCart) {
                
                k+="Model: "+i.getName()+"  Qty: "+i.getQuantity()+"<br>";
         }
            k+="<br>Total: " + ShoppingCart.ShoppingCartTotal()+"</html>";
        }
        cartItems.setText(k);
        buy.repaint();

}
}
