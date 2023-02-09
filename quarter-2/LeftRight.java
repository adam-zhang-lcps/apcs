import java.util.ArrayList;

class LeftRight {
    public static void lr(String cur, int len, ArrayList<String> arr) {
        if (cur.length() == len) {
            arr.add(cur);
        } else {
            lr(cur + "L", len, arr);
            lr(cur + "R", len, arr);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> combo = new ArrayList<String>();
        lr("", 3, combo);
        for (String temp : combo) {
            System.out.println(temp);
        }
    }
}
