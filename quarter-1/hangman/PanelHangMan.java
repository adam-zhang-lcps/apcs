package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelHangMan extends JPanel implements KeyListener {
    JLabel lettersUsedLabel, myWordLabel;
    Hangman myHangman;

    public PanelHangMan() {
        setLayout(new BorderLayout());

        Font myFont20 = new Font("Serif", Font.BOLD, 20);
        Font myFont30 = new Font("Serif", Font.BOLD, 30);

        lettersUsedLabel = new JLabel("");
        lettersUsedLabel.setFont(myFont20);
        lettersUsedLabel.setForeground(Color.red);
        add(lettersUsedLabel, BorderLayout.NORTH);

        String daWord = WordGenerator.getWord();
        System.out.println("word is: " + daWord);
        myHangman = new Hangman(daWord);
        add(myHangman, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        add(panel, BorderLayout.SOUTH);
        JButton button1 = new JButton("Reset");
        button1.addActionListener(new ListenerReset());
        button1.setFocusable(false);
        panel.add(button1);

        myWordLabel = new JLabel(myHangman.getString());
        myWordLabel.setFont(myFont30);
        myWordLabel.setForeground(Color.blue);
        panel.add(myWordLabel);

        this.addKeyListener(this);
        setFocusable(true);

    }

    /*
     * Reset button clear out both labels get another word reset the Hangman object
     */
    private class ListenerReset implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String daWord = WordGenerator.getWord();
            System.out.println("word is: " + daWord);
            myHangman.reset(daWord);
            lettersUsedLabel.setText("");
            myWordLabel.setText(myHangman.getString());

            System.out.println("reset");
        }
    }

    public void keyTyped(KeyEvent e) {
        displayInfo(e, "KEY TYPED: ");
        char c = e.getKeyChar();

        /*
         * call the Hangman method to see if this character is in the answer string
         */
        boolean result = myHangman.tryCharacter(c);
        if (!result) {
            String str = lettersUsedLabel.getText();
            lettersUsedLabel.setText(str + c);
        }
        myWordLabel.setText(myHangman.getString());
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        /*
         * int keyCode = e.getKeyCode(); System.out.println("key pressed: " + keyCode);
         */
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
    }

    private void displayInfo(KeyEvent e, String keyStatus) {

        // You should only rely on the key char if the event
        // is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";

        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        }

        System.out.println("output:: " + keyString);

    }

}
