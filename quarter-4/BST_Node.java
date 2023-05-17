
public class BST_Node<K extends Comparable<K>, V> implements Comparable<BST_Node<K, V>> {
    private K key;
    private V value;
    private BST_Node<K, V> left;
    private BST_Node<K, V> right;

    public BST_Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BST_Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(BST_Node<K, V> left) {
        this.left = left;
    }

    public BST_Node<K, V> getRight() {
        return right;
    }

    public void setRight(BST_Node<K, V> right) {
        this.right = right;
    }

    @Override
    public int compareTo(BST_Node<K, V> o) {
        return this.key.compareTo(o.getKey());
    }

    @Override
    public String toString() {
        return "BST_Node{" + "key=" + key + ", value=" + value + '}';
    }
}
