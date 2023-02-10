public class Stack {
    private int[] arr = new int[10];
    private int size = 0;

    public void push(int num) {
        if (size == arr.length) {
            resize();
        }

        arr[size++] = num;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }

        return arr[--size];
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private void resize() {
        int[] temp = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
}
