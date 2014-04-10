package project;

import javax.swing.*;
import java.awt.*;
  
class Owner extends JFrame
 {
   Owner()
  {
   Inventory.load();
   
  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
  setTitle("Welcome");
  setSize(400, 200);
   }
  } 