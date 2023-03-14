//  Complete LinkedList class with nested helper node class

public class LinkedList {
    private Node head = null; // points to the first node of the list
    private int size = 0;     // the size of the list, starts at 0

    // class for creating node objects that store integers
    // nested in LinkedList since that is the only class that utilizes it
    private class Node {
        int data;  // the data stored at this place in the list
        Node next; // reference to the next node in the list (the link)

        // constructor initializes the data and defaults reference to next node
        // to null
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Internal helper method to get the node at a given index
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // appends a node with the value num to the beginning of the list
    public void addFirst(int num) {
        /* 1. Create a new node object with the value num
         * 2. Set the new node's next field to reference the current head node
         *   (The previous head node is now at position 2)
         * 3. Set the head field to point to the new node
         * 4. Increase the size field by 1
         */
        Node newNode = new Node(num);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Append a node with the value num to the end of the list.
     * @param num the value to be stored in the new node
     * @return void
     */
    public void addLast(int num) {
        Node newNode = new Node(num);
        if (head == null) {
            head = newNode;
        } else {
            getNode(size - 1).next = newNode;
        }
        size++;
    }

    /**
     * Print the contents of the list.
     * @return void
     */
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Insert a new value at the given index.
     * @param num the value to be inserted
     * @param index the index at which to insert the new value
     * @return void
     */
    public void insert(int num, int index) {
        Node prev = getNode(index - 1);
        Node next = prev.next;
        Node newNode = new Node(num);

        prev.next = newNode;
        newNode.next = next;
        size++;
    }

    /**
     * Removes the given index from the list.
     * @param index the index to be removed
     * @return the value of the removed node
     */
    public int remove(int index) {
        Node prev = getNode(index - 1);
        Node current = prev.next;
        Node next = current.next;

        prev.next = next;
        size--;
        return current.data;
    }

    /**
     * Returns the value of the node at the given index.
     * @param index the index of the node to be returned
     * @return the value of the node at the given index
     */
    public int get(int index) { return getNode(index).data; }

    /**
     * Returns whether the list contains the given value.
     * @param num the value to be searched for
     * @return true if the list contains the given value
     */
    public boolean contains(int num) {
        Node current = head;
        while (current != null) {
            if (current.data == num) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the given value.
     * @param num the value to be searched for
     * @return the index of the first occurrence of the given value
     */
    public int indexOf(int num) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == num) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Clear the list of all nodes.
     * @return void
     */
    public void clear() {
        head = null;
        size = 0;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.printList();
    }
}
