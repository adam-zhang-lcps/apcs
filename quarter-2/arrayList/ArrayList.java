package arrayList;

public class ArrayList implements List {
  private Integer arr[];
  private int next = 0;

  public ArrayList() { this(10); }

  public ArrayList(int size) { arr = new Integer[size]; }

  private void resizeIfNeeded() {
    if (next != arr.length) {
      return;
    }
    Integer newArr[] = new Integer[arr.length * 2];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    arr = newArr;
  }

  private void printError(String error) {
    System.out.printf("Exception: %s, ignoring operation\n", error);
  }

  @Override
  public boolean add(Integer value) {
    resizeIfNeeded();
    arr[next] = value;
    next++;
    return true;
  }

  @Override
  public void add(int index, Integer value) {
    if (index < 0 || index > next) {
      printError("invalid index");
      return;
    }
    resizeIfNeeded();
    for (int i = next; i > index; i--) {
      arr[i] = arr[i - 1];
    }
    arr[index] = value;
    next++;
  }

  @Override
  public Integer remove(int index) {
    if (index < 0 || index >= next) {
      printError("invalid index");
      return null;
    }
    Integer value = arr[index];
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
  public Integer get(int index) {
    if (index < 0 || index >= next) {
      printError("invalid index");
      return null;
    }
    return arr[index];
  }

  @Override
  public int indexOf(Integer value) {
    for (int i = 0; i < next; i++) {
      if (arr[i].equals(value)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public Integer set(int index, Integer value) {
    if (index < 0 || index >= next) {
      printError("invalid index");
      return null;
    }
    Integer oldValue = arr[index];
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
