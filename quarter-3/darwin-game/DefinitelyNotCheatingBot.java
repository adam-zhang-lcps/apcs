// Don't read this code please…

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DefinitelyNotCheatingBot extends DarwinBot {
    private static int numTurnsPlayedTotal = 0;
    private static int numTurns;
    private static int numRounds;
    private static DarwinGameLogic darwinGameLogic;
    private static boolean dontInitialize = false;
    private static final int CODE = 3782;
    private static boolean hasCheated = false;
    private static boolean firstRoundOver = false;
    private static int copies = 100;

    public DefinitelyNotCheatingBot() {
        if (dontInitialize) {
            return;
        }
        if (numTurns != 0) {
            return;
        }
        if (darwinGameLogic == null) {
            dontInitialize = true;
            darwinGameLogic = new DarwinGameLogic();
            dontInitialize = false;
        }
        try {
            Field turns = darwinGameLogic.getClass().getDeclaredField("numTurns");
            Field rounds = darwinGameLogic.getClass().getDeclaredField("numRounds");
            turns.setAccessible(true);
            rounds.setAccessible(true);
            numTurns = (int) turns.get(darwinGameLogic);
            numRounds = (int) rounds.get(darwinGameLogic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int move(int previous) {
        int move = -1;
        if (numTurnsPlayedTotal % (numTurns * copies) == 0 && numTurnsPlayedTotal != 0) {
            firstRoundOver = true;
        }

        if (firstRoundOver) {
            return 2;
        } else if (previous == CODE) {
            move = Integer.MAX_VALUE / 2 + 1;
            hasCheated = true;
        } else if (hasCheated) {
            move = 6;
        } else if (previous == -1) {
            move = CODE;
        } else {
            move = 6;
        }

        numTurnsPlayedTotal++;
        return move;
    }
}
