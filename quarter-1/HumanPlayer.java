import java.util.Scanner;

public class HumanPlayer extends GamePlayer {
    /**
     * Constructs a new HumanPlayer that represents human playing with the given
     * playerName
     *
     * @param playerName the name of the player
     */
    public HumanPlayer(String playerName) {
        super(playerName);
    }

    @Override
    /**
     * The player will be prompted to make a choice of weapons
     */
    public void makeChoice() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter choice (r/p/s):");
        switch (s.nextLine()) {
        case "r":
            setChoice(ROCK);
            break;
        case "p":
            setChoice(PAPER);
            break;
        case "s":
            setChoice(SCISSORS);
        }
        s.close();
    }

}
