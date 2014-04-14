

package project;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import static project.Inventory.inventory;


public class FileChooserDemo extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    static private JFrame frame1 = new JFrame("Choosee a picture");
    
    
        final int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
    final int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
    
    
    static private String ID;
    static private String name;
    static private double invoicePrice;
    static private double sellingPrice;
    static private String type;
    static private int quantity ;

    
    static public String finameChosen;

    public FileChooserDemo() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save  Item...");
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                finameChosen =file.getName();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {

            System.out.println(finameChosen);
            
            Image myimage = null ;
                try {
                    myimage = ImageIO.read(new File(finameChosen));
                } catch (IOException ioe) {
                }
                
                Image newimg = myimage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);  
                ImageIcon icon = new ImageIcon(newimg); 

            
                Item i = new Item( icon,name, ID, type,invoicePrice,sellingPrice ,quantity,finameChosen);

                inventory.add(i);
                Inventory.save();
                frame1.dispose();
                Owner page = new Owner();
                page.setLocation(x - 600, y - 300);
                page.setVisible(true);

               
                    
        }
    }


    public static String createAndShowGUI(String othername, String otherID, String othertype, double otherinvoice,
            double otherselling, int otherquantity) {
        //Create and set up the window.
         frame1.setVisible(true);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
           
           name= othername;
           ID = otherID;
           type = othertype;
           invoicePrice = otherinvoice;
           sellingPrice = otherselling;
           quantity = otherquantity;
    
    
        
        //Add content to the window.
        frame1.add(new FileChooserDemo());

        //Display the window.
        frame1.pack();
        frame1.setVisible(true);
        return finameChosen;
        
    }


}

