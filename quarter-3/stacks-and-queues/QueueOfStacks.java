import java.util.Stack;

public class QueueOfStacks {
    private Stack<Integer> queued = new Stack<Integer>();
    private Stack<Integer> ordered = new Stack<Integer>();
    private int size = 0;

    public void enqueue(int v) {
        queued.push(v);
        size++;
    }
    public int dequeue() {
        if (ordered.isEmpty()) {
            while (!queued.isEmpty()) {
                ordered.push(queued.pop());
            }
        }
        size--;
        return ordered.pop();
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
