
//Programmer:  Patrick Sliwinski
//John Chapin:  Modified for colors, shuffle, class lab, Threads
//Chris Schroeder: Modified to update the Swing UI on demand instead of using two threads and timers.

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;

public class SelectionSortPanel extends JPanel implements ISortPanel {

  private BufferedImage myImage;
  private final int scale = 100;
  private final int screenSize = 1000;
  private final int delayLength = 300;

  public SelectionSortPanel() {

    // initialize array with random values
    int myArray[] = generateArray();

    //create a sorting object
    SelectionSort sort = new SelectionSort(myArray, this);

    //set up the graphics area
    getPanelGraphics();

    //create thread for sorting
    Thread sortingThread = new Thread(sort);

    //*******************************************
    //what does sortingThread.start() do?
    //
    //******************************************
    sortingThread.start();
  }

  /**
   * Implement the drawPanel method, hiding the Swing UI elements from the sorting object
   *
   * @param anArray - The array to update
   */
  public void updateArray(final int anArray[])  {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          drawArray(anArray);
          try {
            Thread.sleep(delayLength);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void paintComponent(Graphics g) {
    g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
  }

  /**
   * Get the graphics area we are painting to. If the image area does not exists, then create it first
   * @return Graphics area to pain to....
   */
  private Graphics getPanelGraphics(){
    //set up the buffer
    if (null == myImage){
      myImage = new BufferedImage(screenSize, screenSize, BufferedImage.TYPE_INT_RGB);
    }
    Graphics graphics = myImage.getGraphics();
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, screenSize, screenSize);
    return graphics;
  }

  /**
   * Draw the given array onto the panel. This redraws everything instead of painting the difference.
   * @param anArray - the array to draw
   */
  private void drawArray(int anArray[]) {
    // Paste black background on screen to "clear it"
    Graphics graphics = getPanelGraphics();

    // Draw each rectangle
    for (int i = 0; i < anArray.length; i++) {

      // set rectangle color based on value in array
      int scaleColor = anArray[i] * 255 / scale;
      int red = 255 - scaleColor;
      int green = scaleColor;
      int blue = 255 - scaleColor / 4;
      Color rectColor = new Color(red, green, blue);
      graphics.setColor(rectColor);

      // draw rectangle
      graphics.fillRect((screenSize / anArray.length) * i,
        screenSize - (((screenSize - 100) / scale) * anArray[i]), (screenSize / anArray.length) / 2,
        (((screenSize - 100) / scale) * anArray[i]));

      // draw number on rectangle
      graphics.drawString(anArray[i] + "", (screenSize / anArray.length) * i,
        screenSize - (((screenSize - 100) / scale) * anArray[i]) - 15);
    }

    repaint();
  }

  /**
   * Generate a new array that contains random numbers is is properly shuffled.
   * @return A newly minted array of integers in all kinds of fun random order.
   */
  private int[] generateArray(){
    // set array to random size from 29 to 49 elements
    int size = (int) (Math.random() * 30 + 20);

    // initialize array with random values
    int newArray[] = new int[size];
    for (int i = 0; i < size; i++) {
      newArray[i] = (int) (Math.random() * scale + 1);
    }

    return shuffleArray(newArray);
  }

  // Implementing Fisher/Yates shuffle
  private int[] shuffleArray(int anArray[]) {

    Random rnd = new Random();
    for (int i = anArray.length - 1; i > 0; i--) {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = anArray[index];
      anArray[index] = anArray[i];
      anArray[i] = a;
    } // for
    return anArray;
  }// shuffleArray
}
