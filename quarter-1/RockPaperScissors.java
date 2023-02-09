import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RockPaperScissors {

    public static final int HUMAN = 1;
    public static final int COMPUTER = 2;
    public static final int ROCK_LOVER = 3;
    public static final int SCISSOR_LOVER = 4;

    /** {@value #DEFAULT_WINNING_SCORE} the number of wins to win the game */
    public static final int DEFAULT_WINNING_SCORE = 2;

    public static void main(String[] args) {

        GamePlayer player1;
        GamePlayer player2;

        player1 = choosePlayerType();
        player2 = choosePlayerType();

        int winningScore = getWinningScore(); // Used in Extension 1

        do {
            player1.makeChoice();
            player2.makeChoice();

            showChoices(player1, player2);

            GamePlayer winner = compareChoices(player1, player2);
            if (winner != null) {
                winner.win();
            }

            printWinner(winner);
            printRecord(player1);
            printRecord(player2);
        } while (player1.getWins() < winningScore &&
                 player2.getWins() < winningScore);

        printEndGameMessage();
    }

    /**
     * Asks for the number of wins to win the game. If the value is less than or
     * equal to zero, then, by default, the winning score will be
     * {@value #DEFAULT_WINNING_SCORE}
     *
     * @return the number of wins to win the game
     */
    private static int getWinningScore() {
        Scanner s = new Scanner(System.in);
        System.out.println("Number of wins to win:");
        s.close();
        return Integer.parseInt(s.nextLine());
    }

    /**
     * Prints the record of the given <code>Player</code>
     *
     * @param player the player of the game
     */
    private static void printRecord(GamePlayer player) {
        System.out.println(player);
    }

    /**
     * Prints the winner of the round
     *
     * @param winner the <code>GamePlayer</code> who is the winner of the round
     */
    private static void printWinner(GamePlayer winner) {
        if (winner == null) {
            System.out.println("Tie");
        } else {
            System.out.println(winner.getName() + " wins!!");
        }
        System.out.println();
    }

    /**
     * Compares and returns who wins the round.
     *
     * @param player1 a <code>GamePlayer</code>
     * @param player2 a <code>GamePlayer</code>
     * @return returns the <code>GamePlayer</code> who wins the game,
     *         <code>null</code> if there is a tie
     */
    private static GamePlayer compareChoices(GamePlayer player1,
                                             GamePlayer player2) {
        int winning = (player1.getChoice() + 1) / 4;
        if (player2.getChoice() == winning) {
            return player2;
        } else if (player1.getChoice() == player2.getChoice()) {
            return null;
        } else {
            return player1;
        }
    }

    /**
     * Prints the choices for both players
     *
     * @param player1 a player
     * @param player2 a player
     */
    private static void showChoices(GamePlayer player1, GamePlayer player2) {

        System.out.println();
        System.out.println(player1.getName() + ": " + player1.getChoiceName() +
                           "   vs.  " + player2.getName() + ": " +
                           player2.getChoiceName());
        System.out.println();
    }

    /**
     * Prompts the choices and returns the types of players to play the game
     *
     * @return the type of player to play the game
     */
    private static GamePlayer choosePlayerType() {
        try {
            BufferedReader r =
                new BufferedReader(new InputStreamReader(System.in));
            int type;
            String name = "";
            GamePlayer player;

            System.out.println();
            System.out.println("Choose a type of player:");
            System.out.println("\t1. Human");
            System.out.println("\t2. Computer");
            System.out.println("\t3. Rock Lover");
            System.out.println("\t4. Scissor Lover");
            System.out.print("Make your choice: ");

            type = Integer.parseInt(r.readLine());

            if (type == HUMAN) {
                System.out.print("What is your name: ");
                name = r.readLine();
                player = new HumanPlayer(name);
            } else if (type == COMPUTER) {
                player =
                    new ComputerPlayer("Skynet"); // You may change this name
            } else if (type == SCISSOR_LOVER) {
                player = new ScissorLoverPlayer(
                    "Your Mom"); // You may change this name
            } else {
                player = new RockLoverPlayer(
                    "Bart Simpson"); // You may change this name
            }

            return player;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Prints a message when the game is over.
     */
    private static void printEndGameMessage() {
        System.out.println("Game over");
    }
}
