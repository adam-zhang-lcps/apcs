/* TODO:
 * Rounding errors cause pool to end up at 199, give dominate bot the last spot?
 * Sort bot numbers report after rounds
 * Test with some real bots
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DarwinGameLogic {
    private ArrayList<DarwinBot> pool =
        new ArrayList<DarwinBot>(); // all bots in the ENTIRE pool.
    private ArrayList<String> botNames =
        new ArrayList<String>(); // names of all bots participating
    private int[] botScores;
    private int poolSize;              // size of the pool (numBots * 100)
    private final int numTurns = 150;  // number of turns in each match
    private final int numRounds = 200; // number of rounds for a game

    // set up initial fields
    public DarwinGameLogic() {
        setUpBotNames(); // fill botNames list
        poolSize = botNames.size() * 100;
        setUpPool(true); // fill pool with initial bots
    }

    // get list of bot names from file
    private void setUpBotNames() {
        // Get file name and open file
        // Scanner nameScanner = new Scanner(System.in);
        // System.out.println("Filename? (include .txt extension)");
        // String fileName = nameScanner.nextLine();
        String fileName = "quarter-3/darwin-game/bots.txt";
        File file = new File(fileName);
        // read from file and fill botNames list
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                botNames.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error finding file");
        }
        // set up botScores array
        botScores = new int[botNames.size()];
    }

    // set up the pool at the start of the game or end of a round
    // start is true if this is the first call at the start of the game
    private void setUpPool(boolean start) {
        pool.clear(); // clear out the pool

        // calculate total points for percentage calculations
        int totalPoints = 0;
        for (int i = 0; i < botScores.length; i++) {
            totalPoints += botScores[i];
        }
        System.out.println("Total points: " + totalPoints);

        // loop over botNames list and add appropriate number of each to the
        // pool
        for (int i = 0; i < botNames.size(); i++) {
            String name = botNames.get(i);
            // determine number of bots to be added
            int numBots = 0;
            if (!start) {
                // current bot's score / total score = percentage of next pool
                double percent = (double)botScores[i] / totalPoints;
                numBots = (int)(poolSize * percent);
                System.out.println(name + ": " + botScores[i]);
                System.out.println(name + ": " + numBots);
            } else {
                numBots = 100; // initial number of bots at the start of the
                               // game
            }

            // add bots
            for (int j = 0; j < numBots; j++) {
                // weird code from Google, seems to work?
                DarwinBot newBot = null;
                try {
                    Class cls = Class.forName(name);
                    newBot = (DarwinBot)cls.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                pool.add(newBot);
            }
        }
    }

    // plays a single round of the game
    // every bot in pool plays a match against a random opponent
    private void playRound() {
        // repeat as long as there are 2 bots in the pool to match
        while (pool.size() > 1) {
            // set up player 1
            int index = (int)(Math.random() * pool.size()); // random valid
                                                            // index
            DarwinBot p1 = pool.get(index);                 // pull out bot
            pool.remove(index); // remove from pool so no bot plays twice

            // set up player 2
            index = (int)(Math.random() * pool.size());
            DarwinBot p2 = pool.get(index);
            pool.remove(index);

            // start match
            playMatch(p1, p2);
        }

        // Update pool for next round and reset scores
        setUpPool(false);
        for (int i = 0; i < botScores.length; i++) {
            botScores[i] = 0;
        }
    }

    // play a single match
    private void playMatch(DarwinBot p1, DarwinBot p2) {
        // System.out.println(p1 + " vs " + p2);
        int p1Prev = -1; // p1s last move
        int p2Prev = -1; // p2s last move

        // play out all the turns of the match
        for (int i = 0; i < numTurns; i++) {
            // get moves
            int p1Move = p1.move(p2Prev);
            int p2Move = p2.move(p1Prev);
            // System.out.println(p1Move + " " + p2Move);

            // negative number check
            if (p1Move < 0) {
                p1Move = 0;
            }
            if (p2Move < 0) {
                p2Move = 0;
            }

            // update previous values
            p1Prev = p1Move;
            p2Prev = p2Move;

            // process moves and calculate scores
            System.out.println(p1Move + " " + p2Move);
            System.out.println("total: " + (p1Move + p2Move));
            if (p1Move + p2Move <= 5) {
                addScore(p1, p1Move);
                addScore(p2, p2Move);
            }
        }
    }

    // adds the given value to the given bot's score
    private void addScore(DarwinBot bot, int score) {
        // aquire class name of the given object as a String
        String name = bot.getClass() + "";
        name = name.substring(6);

        // find position of the bot in botNames and update the corresponding
        // score
        for (int i = 0; i < botNames.size(); i++) {
            if (botNames.get(i).equals(name)) {
                System.out.println("Adding " + score + " to " + name);
                botScores[i] += score;
                break;
            }
        }
    }

    // play an entire game
    // parameter is how often (in rounds) the game should pause and report
    private void playGame(int pauseInterval) {
        int curRound = 1;
        Scanner s = new Scanner(System.in); // to allow the game to pause
        // iterate the appropriate number of rounds
        for (int i = 0; i < numRounds; i++) {
            playRound();

            // check pause interval and report if needed
            if (curRound % pauseInterval == 0) {
                System.out.println("Results after round " + curRound + ":");

                // get number of each bot in the current pool
                for (String name : botNames) {
                    int count = 0;
                    for (DarwinBot bot : pool) {
                        String curName = bot.getClass() + "";
                        curName = curName.substring(6);
                        if (name.equals(curName)) {
                            count++;
                        }
                    }
                    System.out.println(name + " bots = " + count);
                }

                System.out.println("Press enter to continue...");
                s.nextLine();
            }

            curRound++;
        }
    }

    public static void main(String[] args) {
        DarwinGameLogic game = new DarwinGameLogic();
        game.playGame(1);
    }
}
