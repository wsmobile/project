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
import static project.FileChooserDemo.finameChosen;
import static project.Inventory.inventory;



public class Customer extends JFrame implements ActionListener {


    public JLabel pl1, pl2, pl3, pl4, pl5;
    public JLabel l1, l2, l3, l4, l5;
    public Container con;
    public String[] Sary;
    final Box middle = Box.createVerticalBox();
    final Box buy = Box.createVerticalBox();
    final Box choz = Box.createVerticalBox();
    public static JLabel currentpic = new JLabel("");
    
    public Customer() {

        Inventory.load();

        Sary = new String[5];
        for (int x = 0; x < Sary.length; x++) {
            Sary[x] = "Sary_OrderArray ";
        }

        this.setVisible(true);
        this.setSize(600, 435);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        con = getContentPane();
        con.setLayout(null);

        con.setBackground(Color.white);

        l1 = new JLabel("Jlabel1");
        l2 = new JLabel("JLabel2");
        l3 = new JLabel("");
        l4 = new JLabel("");
        l5 = new JLabel("");


  
    

//Submit
        JButton Sub = new JButton(" SUBMIT ");
        Sub.setMaximumSize(new Dimension(120, 25));
        Sub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int x = 0; x < Sary.length; x++) {
                    System.out.println(Sary[x]);
                }

            }
        });

        buy.add(l1);
        buy.add(l2);
        buy.add(l3);
        buy.add(l4);
        buy.add(l5);

        
        
  //remove
        JButton rem = new JButton(" Remove ");
        rem.setMaximumSize(new Dimension(120, 25));
        rem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int x = 0; x < Sary.length; x++) {
                    Sary[x] = " ";
                }
                l1.setText(" ");
                l2.setText(" ");
                l3.setText(" ");
                l4.setText(" ");
                l5.setText(" ");

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
        con.add(middle);
   
        
    //// Buy box - 3rd box      
        buy.setVisible(true);
        buy.setBounds(400, 0, 200, 450);
        buy.setBorder(BorderFactory.createTitledBorder("Selected items"));
        con.add(buy);

        buy.add(Box.createRigidArea(new Dimension(0, 50)));

        ImageIcon image = new ImageIcon("1.jpg");

        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
        buy.add(label1);
        buy.add(Sub);
        buy.add(rem);

    }
    



    @Override

    public void actionPerformed(ActionEvent e) {

   // TODO Auto-generated method stub
    }
    
    private void displayItem(String n){
        
        
        for (Item i : inventory) {
          
            if (i.getName().equals(n)) {
                                
                System.out.println("Display "+n);
                
                Image myimage = null ;
                try {
                    myimage = ImageIO.read(new File(i.getfilename()));
                } catch (IOException ioe) {
                }

                Image newimg = myimage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                
                currentpic.setIcon(icon);
                currentpic.repaint();

                
                break;
            }
    }

}
}
