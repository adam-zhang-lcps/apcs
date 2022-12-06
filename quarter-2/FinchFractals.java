/* This lab is from Lesson 15: Finch Fractals
 * from https://learn.birdbraintechnologies.com/finch/java/program/lesson-15-finch-fractals?wvideo=rvk8lp96lj
 */

public class FinchFractals {
    private static Finch finch = new Finch();

    /* This method calls itself recursively to draw a Koch fractal. */
    public static void drawFractal(int order, double distance) {
        if (order == 0) {
            finch.setMove("F", distance, 50);
        } else {
            drawFractal(order - 1, distance / 3);
            finch.setTurn("L", 60, 50);
            drawFractal(order - 1, distance / 3);
            finch.setTurn("R", 120, 50);
            drawFractal(order - 1, distance / 3);
            finch.setTurn("L", 60, 50);
            drawFractal(order - 1, distance / 3);
        }
    }

    public static void main(String[] args) {
        // call the drawFractal method for an order and distance
        drawFractal(2, 50);
        finch.setTurn("R", 120, 50);

        // Draw two more fractals to create a snowflake
        drawFractal(2, 50);
        finch.setTurn("R", 120, 50);
        drawFractal(2, 50);

        // Close connection to your finch
        finch.disconnect();
    }
}
