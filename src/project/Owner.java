package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
      this.setLayout(new GridLayout(15, 1));
      
      
      //panel for current items
      for(Item i : inventory){
        
        JPanel CurrentItemPanel = new JPanel(new GridLayout(1, 14));
        
        CurrentItemPanel.add(new JLabel(i.picture));
        CurrentItemPanel.add(new JLabel("Name:"));
        CurrentItemPanel.add(new JLabel(i.name));
        CurrentItemPanel.add(new JLabel("Type:"));
        CurrentItemPanel.add(new JLabel(i.type));
        CurrentItemPanel.add(new JLabel("ID:"));
        CurrentItemPanel.add(new JLabel(i.ID));
        CurrentItemPanel.add(new JLabel("Invoice Price:"));
        CurrentItemPanel.add(new JTextField((int) i.invoicePrice));        ////fixthis cant print doubles toJtextField
        CurrentItemPanel.add(new JLabel("Selling Price:"));
        CurrentItemPanel.add(new JTextField((int) i.sellingPrice));
        CurrentItemPanel.add(new JLabel("Quantity:"));
        CurrentItemPanel.add(new JTextField((int) i.quantity));
        CurrentItemPanel.add(new JButton("Remove"));
               
        this.add(CurrentItemPanel);

        }
      
      
      //panel for dashing
        JPanel SpacePanel = new JPanel(new GridLayout(1, 1));
        SpacePanel.add(new JLabel("To create new item fill the form below:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        add(SpacePanel);
        
        
        
        //panel for creating new item
        JPanel CreateNewItemPanel = new JPanel(new GridLayout(1, 14));
        JButton CreateButton = new JButton("Create");
        
        CreateNewItemPanel.add(new JLabel("i.picture)"));
        CreateNewItemPanel.add(new JLabel("Name:"));
        CreateNewItemPanel.add(new JTextField("i.name"));
        CreateNewItemPanel.add(new JLabel("Type:"));
        CreateNewItemPanel.add(new JTextField("i.type"));
        CreateNewItemPanel.add(new JLabel("ID:"));
        CreateNewItemPanel.add(new JTextField("i.ID"));
        CreateNewItemPanel.add(new JLabel("Invoice Price:"));
        CreateNewItemPanel.add(new JTextField("(int) i.invoicePrice"));       
        CreateNewItemPanel.add(new JLabel("Selling Price:"));
        CreateNewItemPanel.add(new JTextField("(int) i.sellingPrice"));
        CreateNewItemPanel.add(new JLabel("Quantity:"));
        CreateNewItemPanel.add(new JTextField("(int) i.quantity"));
        CreateNewItemPanel.add(CreateButton);
        CreateNewItemPanel.setBackground(Color.gray);
        
        add(CreateNewItemPanel);
        
           CreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            /////////////get info from panel and create new item
                
           

            }
        });
           
           
      //panel for seller info
//      Profit = Revenues - Costs, 
// Revenues = Sum of sell price for all sold items
// Costs = Sum of invoice price for all items brought in the inventory (bought) 
//        JPanel InfoPanel = new JPanel(new GridLayout(1, 14));
//        //SpacePanel.add(new JLabel("TOTALS------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
//        SpacePanel.add(new JLabel("Costs:"));
//        SpacePanel.add(new JLabel("0"));
//        SpacePanel.add(new JLabel("Revenues:"));
//        SpacePanel.add(new JLabel("0"));
//        SpacePanel.add(new JLabel("Profit:"));
//        SpacePanel.add(new JLabel("0"));
//        
//        add(InfoPanel);
           
           
           
  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
  setTitle("Welcome");
  setSize(1150, 400);
  //setLocation(150, 150); ///not working location is being set from HomePage.java
   }
  } 