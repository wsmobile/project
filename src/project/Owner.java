package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static project.Inventory.inventory;
  
class Owner extends JFrame
 {
   Owner()
  {

    
    
    
//  Inventory.createTestItemz();
//  Inventory.save();
  Inventory.loadz();
  
        for(Item i : inventory){
            
        System.out.println(i.name+"\t"+i.type+"\t"+i.ID+"\t"+i.sellingPrice+"\t"+i.quantity);
        }
   
        
              //displaying in window
      this.setLayout(new GridLayout(10, 1));
      for(Item i : inventory){
        
        JPanel panel = new JPanel(new GridLayout(1, 13));
        
        panel.add(new JLabel(i.picture));
        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(i.name));
        panel.add(new JLabel("Type:"));
        panel.add(new JLabel(i.type));
        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(i.ID));
        panel.add(new JLabel("Invoice Price:"));
        panel.add(new JTextField((int) i.invoicePrice));        ////fixthis cant print doubles toJtextField
        panel.add(new JLabel("Selling Price:"));
        panel.add(new JTextField((int) i.sellingPrice));
        panel.add(new JLabel("Quantity:"));
        panel.add(new JTextField((int) i.quantity));
        
        
        
        
        this.add(panel);

        }
        
  
  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
  setTitle("Welcome");
  setSize(1100, 400);
  //setLocation(150, 150); ///not working location is being set from HomePage.java
   }
  } 