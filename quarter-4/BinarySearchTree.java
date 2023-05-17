public class BinarySearchTree<K extends Comparable<K>, V> {
    private BST_Node<K, V> root;
    private int size;

    public int size() {
        return size;
    }

    private BST_Node<K, V> recGetRef(BST_Node<K, V> current, K key) {
        if (current == null) {
            return null;
        }

        int comparison = key.compareTo(current.getKey());
        if (comparison == 0) {
            return current;
        } else if (comparison < 0) {
            return recGetRef(current.getLeft(), key);
        } else {
            return recGetRef(current.getRight(), key);
        }
    }

    public V get(K key) {
        BST_Node<K, V> node = recGetRef(root, key);
        if (node == null) {
            return null;
        }

        return node.getValue();
    }

    private void recAdd(BST_Node<K, V> current, BST_Node<K, V> node) {
        int comparison = node.getKey().compareTo(current.getKey());
        if (comparison < 0) {
            if (current.getLeft() == null) {
                current.setLeft(node);
                return;
            }
            recAdd(current.getLeft(), node);
            return;
        }
        if (current.getRight() == null) {
            current.setRight(node);
            return;
        }
        recAdd(current.getRight(), node);
    }

    public void add(K key, V value) {
        BST_Node<K, V> node = recGetRef(root, key);
        if (node != null) {
            node.setValue(value);
            return;
        }

        node = new BST_Node<K, V>(key, value);
        if (root == null) {
            root = node;
            size = 1;
            return;
        }

        recAdd(root, node);
        size++;
    }

    private int getDepthRec(BST_Node<K, V> current) {
        if (current == null) {
            return 0;
        }

        return 1 + Math.max(getDepthRec(current.getLeft()), getDepthRec(current.getRight()));
    }

    public int getDepth() {
        return getDepthRec(root);
    }

    private void recBreadthFirstSearch(BST_Node<K, V> current, int depth) {
        if (current == null) {
            return;
        }

        if (depth == 0) {
            System.out.printf("<%s, %s> ", current.getKey(), current.getValue());
            return;
        }

        recBreadthFirstSearch(current.getLeft(), depth - 1);
        recBreadthFirstSearch(current.getRight(), depth - 1);
    }

    public void breadthFirstSearch() {
        System.out.println("Breadth First Search:");
        int depth = getDepth();
        for (int i = 0; i < depth; i++) {
            recBreadthFirstSearch(root, i);
        }
    }

    private void recDepthFirstSearch(BST_Node<K, V> current) {
        if (current == null) {
            return;
        }

        recDepthFirstSearch(current.getLeft());

        System.out.printf("<%s, %s> ", current.getKey(), current.getValue());

        recDepthFirstSearch(current.getRight());
    }

    public void depthFirstSearch() {
        System.out.println("Depth First Search:");
        recDepthFirstSearch(root);
    }

    public static void testGet(BinarySearchTree<Integer, Integer> bst, Integer key) {
        Integer rVal = bst.get(key);
        System.out.printf("get(%d)=%s\n", key, rVal);
    }

    public static void main(String[] args) {
        // suggestion, draw out the tree on paper to verify depth, BFS, and DFS
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.add(5, 500);
        bst.add(3, 300);
        bst.add(9, 900);
        bst.add(12, 1200);
        bst.add(14, 1400);
        bst.add(11, 1100);
        bst.add(10, 1000);
        System.out.println("size: " + bst.size());

        testGet(bst, 5);
        testGet(bst, 3);
        testGet(bst, 9);
        testGet(bst, 12);
        testGet(bst, 14);
        testGet(bst, 11);
        testGet(bst, 10);
        testGet(bst, 1);
        testGet(bst, 2);
        testGet(bst, 8);
        testGet(bst, 13);
        testGet(bst, 99);

        System.out.println("depth: " + bst.getDepth());
        bst.breadthFirstSearch();
        System.out.println();

        bst.depthFirstSearch();
        System.out.println();

    }
}
