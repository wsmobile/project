
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import static project.Inventory.inventory;

//loop for buttons
    
//    	for (i=0; i<colors.length; i++) {
//	    final Color c = colors[i];
//	    buttons[i] = new JButton(colorNames[i]);
//
//	    // use anonymous class:
//	    ActionListener al = new ActionListener() {
//		    public void actionPerformed(ActionEvent event) {
//			icon.setColor(c);
//			lbl.repaint();
//		    }
//		};
//
//	    // ... OR could have called: 
//	    //     ActionListener al = new ColorButtonAL(colors[i], icon, lbl);
//	    // OK, it's less to type with anonymous classes.
//
//	    buttons[i].addActionListener(al);
//	    panel.add(buttons[i]);
//	}  // for loop
//    
//



//
//         JButton saveallButton = new JButton("SaveAll");
//
//        saveallButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//               inventory.save();
//            }
//        });
//
//
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import javax.swing.*;
//
//public class SimpleFileChooser extends JFrame {
//
//   public SimpleFileChooser() {
//    super("File Chooser Test Frame");
//    setSize(350, 200);
//    setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//    Container c = getContentPane();
//    c.setLayout(new FlowLayout());
//    
//    JButton openButton = new JButton("Open");
//    JButton saveButton = new JButton("Save");
//    JButton dirButton = new JButton("Pick Dir");
//    final JLabel statusbar = 
//                 new JLabel("Output of your selection will go here");
//
//    // Create a file chooser that opens up as an Open dialog
//    openButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setMultiSelectionEnabled(true);
//        int option = chooser.showOpenDialog(SimpleFileChooser.this);
//        if (option == JFileChooser.APPROVE_OPTION) {
//          File[] sf = chooser.getSelectedFiles();
//          String filelist = "nothing";
//          if (sf.length > 0) filelist = sf[0].getName();
//          for (int i = 1; i < sf.length; i++) {
//            filelist += ", " + sf[i].getName();
//          }
//          statusbar.setText("You chose " + filelist);
//        }
//        else {
//          statusbar.setText("You canceled.");
//        }
//      }
//    });
//
//    // Create a file chooser that opens up as a Save dialog
//    saveButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//        JFileChooser chooser = new JFileChooser();
//        int option = chooser.showSaveDialog(SimpleFileChooser.this);
//        if (option == JFileChooser.APPROVE_OPTION) {
//          statusbar.setText("You saved " + ((chooser.getSelectedFile()!=null)?
//                            chooser.getSelectedFile().getName():"nothing"));
//        }
//        else {
//          statusbar.setText("You canceled.");
//        }
//      }
//    });
//
//    // Create a file chooser that allows you to pick a directory
//    // rather than a file
//    dirButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int option = chooser.showOpenDialog(SimpleFileChooser.this);
//        if (option == JFileChooser.APPROVE_OPTION) {
//          statusbar.setText("You opened " + ((chooser.getSelectedFile()!=null)?
//                            chooser.getSelectedFile().getName():"nothing"));
//        }
//        else {
//          statusbar.setText("You canceled.");
//        }
//      }
//    });
//
//    c.add(openButton);
//    c.add(saveButton);
//    c.add(dirButton);
//    c.add(statusbar);
//  }
//
//  public static void main(String args[]) {
//    SimpleFileChooser sfc = new SimpleFileChooser();
//    sfc.setVisible(true);
//  }
//}
