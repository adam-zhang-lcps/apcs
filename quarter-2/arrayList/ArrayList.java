package arrayList;

public class ArrayList<T> implements List<T> {
    private T arr[];
    private int next = 0;

    public ArrayList() { this(10); }

    public ArrayList(int size) { arr = (T[]) new Object[size]; }

    private void resizeIfNeeded() {
        if (next != arr.length) {
            return;
        }
        T newArr[] = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    @Override
    public boolean add(T value) {
        resizeIfNeeded();
        arr[next] = value;
        next++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > next) {
            throw new IndexOutOfBoundsException(
                String.format("size=%d, index=%d", next, index));
        }
        resizeIfNeeded();
        for (int i = next; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        next++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= next) {
            throw new IndexOutOfBoundsException(
                String.format("size=%d, index=%d", next, index));
        }
        T value = arr[index];
        for (int i = index; i < next - 1; i++) {
            arr[i] = arr[i + 1];
        }
        next--;
        return value;
    }

    @Override
    public int size() {
        return next;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= next) {
            throw new IndexOutOfBoundsException(
                String.format("size=%d, index=%d", next, index));
        }
        return arr[index];
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < next; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T set(int index, T value) {
        if (index < 0 || index >= next) {
            throw new IndexOutOfBoundsException(
                String.format("size=%d, index=%d", next, index));
        }
        T oldValue = arr[index];
        arr[index] = value;
        return oldValue;
    }

    @Override
    public String toString() {
        String str = "";

        for (int k = 0; k < next; k++) {
            str += String.format("%5s,", arr[k]);
            if ((k + 1) % 10 == 0)
                str += String.format("\n");
        }

        return str;
    }
}
