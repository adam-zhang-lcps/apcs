//Programmer:  Patrick Sliwinski
//John Chapin:  Modified for colors and class lab

import javax.swing.JFrame;

public class SelectionSortDriver {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Sorting");
    frame.setSize(1000, 1000);
    frame.setLocation(430, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new SelectionSortPanel());
    frame.setVisible(true);
  }
}
