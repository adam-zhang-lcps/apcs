
public abstract class DarwinBot {
    /* 	returns int value 0-5
     *	previous contains opponent's last move
     *	previous will be -1 on the first round */
    public abstract int move(int previous);
}
