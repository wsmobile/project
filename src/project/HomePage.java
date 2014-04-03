package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomePage {
	


public static void main(String[] args) {
        
        final JFrame frame = new JFrame("HomePage");
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Log in");
        
 

        
        JPanel panel = new JPanel(new GridLayout(3,2));
    
        panel.add(new JLabel("Enter username:"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Enter password:"));
        panel.add(new JTextField(10));
        panel.add(registerButton);
        panel.add(loginButton);
       
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(frame, "You won.");
			}
		});
        
        
        frame.add(panel, BorderLayout.CENTER);
           
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       //frame.setSize(800, 700);
       frame.pack();
       frame.setVisible(true);
        
    }
	
}
