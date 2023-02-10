import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<Integer>();

        // random stack
        myStack.push(34);
        myStack.push(3);
        myStack.push(31);
        myStack.push(98);
        myStack.push(92);
        myStack.push(23);

        System.out.println("Input: " + myStack);
        Stack<Integer> newStack = sortStack(myStack);
        System.out.println("Output: " + newStack);

        myStack.clear();
        newStack.clear();
        System.out.println();

        // random stack
        myStack.push(3);
        myStack.push(5);
        myStack.push(1);
        myStack.push(4);
        myStack.push(2);
        myStack.push(8);

        System.out.println("Input: " + myStack);
        newStack = sortStack(myStack);
        System.out.println("Output: " + newStack);

        myStack.clear();
        newStack.clear();
        System.out.println();

        // 1-element stack
        myStack.push(23);

        System.out.println("Input: " + myStack);
        newStack = sortStack(myStack);
        System.out.println("Output: " + newStack);

        myStack.clear();
        newStack.clear();
        System.out.println();

        // reverse sorted stack
        myStack.push(6);
        myStack.push(5);
        myStack.push(4);
        myStack.push(3);
        myStack.push(2);
        myStack.push(1);

        System.out.println("Input: " + myStack);
        newStack = sortStack(myStack);
        System.out.println("Output: " + newStack);

        myStack.clear();
        newStack.clear();
        System.out.println();

        // sorted stack
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);

        System.out.println("Input: " + myStack);
        newStack = sortStack(myStack);
        System.out.println("Output: " + newStack);
    }

    public static Stack<Integer> sortStack(Stack<Integer> s1) {
        Stack<Integer> s2 = new Stack<Integer>();
        int temp;

        while (!s1.isEmpty()) {
            temp = s1.pop();

            while (!s2.isEmpty() && s2.peek() > temp) {
                s1.push(s2.pop());
            }

            s2.push(temp);
        }

        return s2;
    }
}
