// There once was a cooperative plan,
// one where we would work as a clan,
// to win this game,
// altogether in fame,
// and that would be the end of the day.
//
// Alas, that didn't occur
// the week leading up was a blur,
// and through fault of my own,
// the seeds were not sown,
//
// and now I'm turning in by myself.

// TL;DR I got really lazy and this bot isn't even good
// (I don't even know if it works, testing is overrated)

import java.util.ArrayList;

public class AdamBot extends DarwinBot {
    private static final boolean DEBUG = false;
    private static int ID = 0;
    private int myID;

    private void debug(String message, Object... args) {
        if (DEBUG) {
            System.out.println(String.format(message, args));
        }
    }

    // Key for a teammate
    private static final int[] KEY = new int[] { 1, 2, 3 };
    // On what turn we send the key
    private static final int KEY_TIME = 3;

    // Track stuff
    private int currentMove = 0;
    private ArrayList<Integer> opponentMoves = new ArrayList<Integer>();
    private ArrayList<Integer> myMoves = new ArrayList<Integer>();
    private boolean isTeamBot = true;

    public AdamBot() {
        myID = ID++;
    }

    @Override
    public int move(int previous) {
        debug("Bot %d speaking:", myID);
        opponentMoves.add(previous);
        debug("Opponent move %d: %d", currentMove - 1, previous);

        int move = -1;
        if (currentMove < KEY_TIME) {
            // Return irrelevant move to confuse opponent
            debug("Move %d: before key time", currentMove);

            if (Math.random() < 0.5) {
                move = 2;
            } else {
                move = 3;
            }
        } else if (!isTeamBot) {
            // isTeamBot starts true, so if it's false, we know we're not
            // playing against a team bot, and never will be, so just return
            // 3 and force an advantage
            debug("Move %d: not a team bot", currentMove);

            move = 3;
        } else if (currentMove == KEY_TIME + KEY.length) {
            // Need to check last digit of keys
            debug("Move %d: checking last digit of key", currentMove);

            if (previous == KEY[KEY.length - 1]) {
                debug("Move %d: opponent key confirmed", currentMove);
                move = 2;
            } else {
                // Not a team bot
                debug("Move %d: opponent is not playing key", currentMove);

                isTeamBot = false;
                move = 3;
            }
        } else if (currentMove > KEY_TIME + KEY.length) {
            // Beyond key check, and we know we're playing a team bot
            // (because of the above if statement)
            debug("Move %d: after key check with team bot", currentMove);

            // Check to make sure our "teammate" isn't defecting
            if (previous != 2) {
                // They're defecting, so no longer a team bot
                debug("Move %d: opponent is defecting", currentMove);

                isTeamBot = false;
                move = 3;
            } else {
                move = 2;
            }
        } else {
            // We're inside the key check
            // Only return the key if the opponent is playing the key,
            // otherwise make not a team bot and return 5
            debug("Move %d: inside key check", currentMove);

            if (currentMove == KEY_TIME) {
                // First move of the key, nothing to check
                debug("Move %d: first move of key", currentMove);

                move = KEY[0];
            } else {
                // Check if opponent is the key
                debug("Move %d: checking opponent's move %d", currentMove, previous);
                if (previous != KEY[currentMove - KEY_TIME - 1]) {
                    // They're not playing the key, so no longer a team bot
                    debug("Move %d: opponent is not playing key", currentMove);

                    isTeamBot = false;
                    move = 5;
                } else {
                    // They're playing the key, so return the next move
                    debug("Move %d: opponent is playing key, continuing", currentMove);
                    move = KEY[currentMove - KEY_TIME];
                }
            }
        }

        debug("Move %d: returning %d", currentMove, move);
        myMoves.add(move);
        currentMove++;
        return move;
    }
}
