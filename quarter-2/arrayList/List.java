package arrayList;

public interface List<T> {
  void add(int index, T element);
  boolean add(T element);
  T get(int index);
  T remove(int index);
  T set(int index, T element);
  int size();
  int indexOf(T element);
}
