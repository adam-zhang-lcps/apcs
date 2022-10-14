package hangman;

import javax.swing.JFrame;

public class DriverHangMan {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Man Hanging");
    frame.setSize(350, 550);
    frame.setLocation(200, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new PanelHangMan());
    frame.setVisible(true);
  }
}
