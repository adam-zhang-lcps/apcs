import java.util.ArrayList;

class OddDigits {
  public static void oddDigits(String cur, int len, ArrayList<String> arr) {
    if (cur.length() == len) {
      arr.add(cur);
    } else {
      for (int i = 1; i <= 9; i += 2) {
        oddDigits(cur + i, len, arr);
      }
    }
  }

  public static void main(String[] args) {
    ArrayList<String> combo = new ArrayList<String>();
    oddDigits("", 3, combo);
    for (String temp : combo) {
      System.out.println(temp);
    }
  }
}
