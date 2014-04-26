package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static project.Inventory.inventory;

/**
 * This is a owner class that serve as a view
 * It displays all items in inventory and allows the owner
 * to add, remove or update items in the inventory.
 * @author sraychev
 */
class Owner extends JFrame {

    //get middle of the screen for positioning
    final int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
    final int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);

    /**
     * This is the default constructor.
     * preconditions: user has been identified and logged in as owner
     * postconditions: none
     */
    Owner() {

//use to initialize inventory
//This will create an initial inventory
//Once inventory created it can be manipulated in owner mode
//   try { 
//  Inventory.createTestItemz(); } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//  Inventory.save();
//  
        
  
        //load current inventory
        Inventory.load();
        
        //setting the frame layout
        setLayout(new GridLayout(inventory.size() + 10, 1));
       

        //panel for current items
        for (int i = 0; i < inventory.size(); i++) {

            final JPanel CurrentItemPanel = new JPanel(new GridLayout(1, 15));
            final JLabel nameLabel = new JLabel(inventory.get(i).getName());
            JLabel imgLabel = new JLabel();
            imgLabel.setIcon(inventory.get(i).getpicture());
            final JFormattedTextField quantityField = new JFormattedTextField();
            quantityField.setValue(inventory.get(i).getQuantity());

            final JFormattedTextField invoicepriceField = new JFormattedTextField();
            invoicepriceField.setValue(inventory.get(i).getInvoicePrice());

            final JFormattedTextField sellingpriceField = new JFormattedTextField();
            sellingpriceField.setValue(inventory.get(i).getSellingPrice());

            /**
             * Create buttons and labels for EACH item from inventory
             */
            JButton updateButton = new JButton("Update");
            JButton removeButton = new JButton("Remove");

            CurrentItemPanel.add(imgLabel);
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

            /**
             * Create an action listener for each update button to corresponding item
             * It saves any changes to item's price and quantity and calls itself to redisplay
             */
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Inventory.setInvetoryItem((double) invoicepriceField.getValue(), (double) sellingpriceField.getValue(), (int) quantityField.getValue(), nameLabel.getText());
                    Inventory.save();
                    Owner page = new Owner();
                    page.setLocation(x - 600, y - 300);
                    page.setVisible(true);

                    Owner.super.dispose();

                }
            });

             /**
             * Create an action listener for each remove button to corresponding item
             * It removes that item from inventory and calls itself to redisplay
             */
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Inventory.removeInvetoryItem(nameLabel.getText());
                    Inventory.save();
                    Owner page = new Owner();
                    page.setLocation(x - 600, y - 300);
                    page.setVisible(true);

                    Owner.super.dispose();

                }
            });

            add(CurrentItemPanel);

        }

        /**
         * Create a panel for spacing
         * and add it to the frame
         */
        JPanel SpacePanel = new JPanel(new GridLayout(1, 1));
        SpacePanel.add(new JLabel("To create new item fill the form below:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        add(SpacePanel);

     


        /**
         * Create a panel for adding new item
         * and add it to the frame.
         */
        JPanel CreateNewItemPanel = new JPanel(new GridLayout(1, 14));
        JButton CreateButton = new JButton("Create");

        final JFormattedTextField nameField = new JFormattedTextField();
        final JFormattedTextField IDField = new JFormattedTextField();
        final JFormattedTextField typeField = new JFormattedTextField();
        final JFormattedTextField sellingpriceField2 = new JFormattedTextField();
        final JFormattedTextField invoicepriceField2 = new JFormattedTextField();
        final JFormattedTextField quantityField2 = new JFormattedTextField();

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
        
        /**
         * Creates an action listener for the create button
         * preconditions: all field must be filled
         * postconditions: a new item was added to inventory
         */
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                if (nameField.getText().isEmpty() || IDField.getText().isEmpty() || typeField.getText().isEmpty()
                        || invoicepriceField2.getText().isEmpty() || sellingpriceField2.getText().isEmpty()
                        || quantityField2.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill out all fields!");
                } else {

                    JFileChooser fc = new JFileChooser();
                    String finameChosen = null;
                    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    int returnVal = fc.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        finameChosen = file.getName();

                    } else {

                    }

             //Handle  action.
                    Image myimage = null;
                    try {
                        myimage = ImageIO.read(new File("WSMobilePictures\\" + finameChosen));
                    } catch (IOException ioe) {
                    }

                    Image newimg = myimage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newimg);

                    Item i = new Item(icon, nameField.getText(), IDField.getText(), typeField.getText(),
                            Double.parseDouble(invoicepriceField2.getText()), Double.parseDouble(sellingpriceField2.getText()),
                            Integer.parseInt(quantityField2.getText()), finameChosen);

                    inventory.add(i);
                    Inventory.save();

                    Owner page = new Owner();
                    page.setLocation(x - 600, y - 300);
                    page.setVisible(true);

                    Owner.super.dispose();
                }

            }
        });

        
        /**
         * Create panel for owner info 
         * and add it to the frame
         */
        JPanel infoButtonsPanel = new JPanel();
        //Button for displaying profits
        JButton info = new JButton("Display Sales Profits");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Inventory.displayProfits();

            }
        });
        infoButtonsPanel.add(info);

        //Button for clearing profits
        JButton clearinfo = new JButton("Clear Profit Record");
        clearinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Inventory.clearProfit();
                Inventory.displayProfits();

            }
        });
        infoButtonsPanel.add(clearinfo);

        add(infoButtonsPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(1250, 500);

    }

}
