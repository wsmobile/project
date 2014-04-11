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

class Owner extends JFrame {

    Owner() {

//    
//  Inventory.createTestItemz();
//  Inventory.save();
        Inventory.load();

        for (Item i : inventory) {

            System.out.println(i.getName() + "\t" + i.getType() + "\t" + i.getID() + "\t" + i.getSellingPrice() + "\t" + i.getQuantity());

        }
 
        //displaying in window
        setLayout(new GridLayout(inventory.size() + 10, 1));

        //panel for current items
        for (int i = 0; i < inventory.size(); i++) {

            final JPanel CurrentItemPanel = new JPanel(new GridLayout(1, 15));

            final JLabel nameLabel = new JLabel(inventory.get(i).getName());

//            final JFormattedTextField nameField = new JFormattedTextField();
//            nameField.setValue(inventory.get(i).getName());
//            final JFormattedTextField typeField = new JFormattedTextField();
//            typeField.setValue(inventory.get(i).getType());
//            final JFormattedTextField IDField = new JFormattedTextField();
//            IDField.setValue(inventory.get(i).getID());
            
            final JFormattedTextField quantityField = new JFormattedTextField();
            quantityField.setValue(inventory.get(i).getQuantity());

            final JFormattedTextField invoicepriceField = new JFormattedTextField();
            invoicepriceField.setValue(inventory.get(i).getInvoicePrice());

            final JFormattedTextField sellingpriceField = new JFormattedTextField();
            sellingpriceField.setValue(inventory.get(i).getSellingPrice());

            JButton updateButton = new JButton("Update");
            JButton removeButton = new JButton("Remove");

        //inventory.get(i).setInvoicePrice((double) invoicepriceField.getValue());
            CurrentItemPanel.add(new JLabel(inventory.get(i).getpicture()));
            CurrentItemPanel.add(new JLabel("Name:"));
            CurrentItemPanel.add(new JLabel(inventory.get(i).getName()));
            CurrentItemPanel.add(new JLabel("Type:"));
            CurrentItemPanel.add(new JLabel(inventory.get(i).getType()));
            CurrentItemPanel.add(new JLabel("ID:"));
            CurrentItemPanel.add(new JLabel(inventory.get(i).getID()));
            CurrentItemPanel.add(new JLabel("Invoice Price:"));
            CurrentItemPanel.add(invoicepriceField);
            CurrentItemPanel.add(new JLabel("Selling Price:"));
            CurrentItemPanel.add(sellingpriceField);
            CurrentItemPanel.add(new JLabel("Quantity:"));
            CurrentItemPanel.add(quantityField);
            
            CurrentItemPanel.add(updateButton);
            CurrentItemPanel.add(removeButton);

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Inventory.setInvetoryItem((double) invoicepriceField.getValue(), (double) sellingpriceField.getValue(), (int) quantityField.getValue(), nameLabel.getText());
                    Inventory.save();
                    CurrentItemPanel.repaint();

                }
            });

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Inventory.removeInvetoryItem(nameLabel.getText());
                    Inventory.save();

                    Owner page = new Owner();
                    page.setVisible(true);

                }
            });

            add(CurrentItemPanel);

        }

        //panel for dashing
        JPanel SpacePanel = new JPanel(new GridLayout(1, 1));
        SpacePanel.add(new JLabel("To create new item fill the form below:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        add(SpacePanel);

        //Scroll Pane
//        final JPanel scrollPanel = new JPanel(new GridLayout(1, 1));
//        JScrollPane horinScroll = new JScrollPane();
//        scrollPanel.add(horinScroll);

        //panel for creating new item
        JPanel CreateNewItemPanel = new JPanel(new GridLayout(1, 14));
        JButton CreateButton = new JButton("Create");

         final JFormattedTextField nameField = new JFormattedTextField();

        final JFormattedTextField IDField = new JFormattedTextField();

        final JFormattedTextField typeField = new JFormattedTextField();

        final JFormattedTextField sellingpriceField2 = new JFormattedTextField();

        final JFormattedTextField invoicepriceField2 = new JFormattedTextField();

        final JFormattedTextField quantityField2 = new JFormattedTextField();
        
        
        CreateNewItemPanel.add(new JLabel("i.picture)"));
        CreateNewItemPanel.add(new JLabel("Name:"));
        CreateNewItemPanel.add(nameField);
        CreateNewItemPanel.add(new JLabel("Type:"));
        CreateNewItemPanel.add(typeField);
        CreateNewItemPanel.add(new JLabel("ID:"));
        CreateNewItemPanel.add(IDField);
        CreateNewItemPanel.add(new JLabel("Invoice Price:"));
        CreateNewItemPanel.add(invoicepriceField2);        ////fixthis cant print doubles toJtextField
        CreateNewItemPanel.add(new JLabel("Selling Price:"));
        CreateNewItemPanel.add(sellingpriceField2);
        CreateNewItemPanel.add(new JLabel("Quantity:"));
        CreateNewItemPanel.add(quantityField2);
        CreateNewItemPanel.add(CreateButton);
        CreateNewItemPanel.setBackground(Color.gray);
       
        
        add(CreateNewItemPanel);
        //add(horinScroll);
        CreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //get info from panel and create new item
                
               Item i = new Item(nameField.getText(), IDField.getText(), typeField.getText(), 
                       Double.parseDouble(invoicepriceField2.getText()), Double.parseDouble(sellingpriceField2.getText()), Integer.parseInt(quantityField2.getText()));
               
               inventory.add(i);
               Inventory.save();
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
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(1200, 400);
        //setLocation(150, 150); ///not working location is being set from HomePage.java
    }
}
