import java.util.ArrayList;

public class Queue {
  private ArrayList<Integer> arr = new ArrayList<Integer>();

  public void enqueue(int num) { arr.add(num); }
  public int dequeue() { return arr.remove(0); }
  public int size() { return arr.size(); }
  public boolean isEmpty() { return arr.size() == 0; }
}
