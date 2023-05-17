import java.util.ArrayList;
public class TrackerBot extends DarwinBot {
    private ArrayList<Integer> opponentMoves = new ArrayList<Integer>();
    private ArrayList<Integer> myMoves = new ArrayList<Integer>();

    public int move(int previous) {
        opponentMoves.add(previous);
        myMoves.add(2);
        return 2;
    }
}
