package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HomePage {

    public static void main(String[] args) {

        final JFrame frame = new JFrame("HomePage");
        //frame.setLocationRelativeTo(null);

        final int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2);
        final int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        frame.setLocation(x - 150, y - 150);

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Log in");
        final JTextField tempusername = new JTextField(10);
        final JPasswordField temppassword = new JPasswordField(10);

        frame.getRootPane().setDefaultButton(loginButton);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Enter username:"));
        panel.add(tempusername);
        panel.add(new JLabel("Enter password:"));
        panel.add(temppassword);
        panel.add(registerButton);
        panel.add(loginButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                File f = new File(tempusername.getText() + ".txt");
                if (f.exists()) {
                    JOptionPane.showMessageDialog(frame, "Username exists!");
                } else {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(tempusername.getText() + ".txt"));

                        out.write(temppassword.getText() + "\n");

                        out.close();
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }
                }

            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                File f = new File(tempusername.getText() + ".txt");

                if (tempusername.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Username was empty!\nPLease enter username.");
                } else if (temppassword.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Password was empty!\nPLease enter apassword.");
                } else if (!f.exists()) {
                    JOptionPane.showMessageDialog(frame, "Username does not exists!\nPlease register.");
                } else if ((tempusername.getText().equals("admin")) && (temppassword.getText().equals("pass"))) {
                    //open in seller mode
                    System.out.println("sellermode");
                    Owner page = new Owner();
                    page.setLocation(x - 600, y - 300);
                    page.setVisible(true);
                    //   frame.setVisible(false);
                    frame.dispose();

                } else {
                    BufferedReader br = null;

                    try {
                        String sCurrentLine;
                        String pass = temppassword.getText();

                        br = new BufferedReader(new FileReader(tempusername.getText() + ".txt"));

                        while ((sCurrentLine = br.readLine()) != null) {
                            if (sCurrentLine.equals(pass)) {
                                //open in buyermode
                                System.out.println("buyermode");
                                
                                Customer pagec = new Customer();
                                pagec.setLocation(x - 325, y - 300);
                                pagec.setVisible(true);
                                
                                frame.dispose();
                              
                                                               
                                
                            } else {
                                JOptionPane.showMessageDialog(frame, "Password does not match!\nPlease try again!");
                            }

                        }
                    } catch (IOException eee) {
                        eee.printStackTrace();
                    }
                }

            }
        });

        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setSize(800, 700);
        frame.pack();
        frame.setVisible(true);

    }

}
