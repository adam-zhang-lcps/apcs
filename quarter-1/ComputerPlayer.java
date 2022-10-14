import java.util.Random;

public class ComputerPlayer extends GamePlayer {
    /**
     * Constructs a new ComputerPlayer that represents human playing with the given
     * playerName
     *
     * @param playerName the name of the player
     */
    public ComputerPlayer(String playerName) {
        super(playerName);
    }

    @Override
    /**
     * The player will randomly choose one of the choices
     */
    public void makeChoice() {
        setChoice(new Random().nextInt(3) + 1);
    }
}
