// import java.util.*;
import arrayList.*;
// import linkList.*;

public class TestArrayList_Strings {

    /**
     * @param args
     */

    // note, this method tests the ArrayList size() method
    // public static void printArray(String msg, ArrayListInteger a)
    public static void printArray(String msg, ArrayList a) {
        if (msg != null)
            System.out.println(msg + ":");

        for (int k = 0; k < a.size(); k++) {
            System.out.printf("%7s, ", a.get(k));
            if ((k + 1) % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void printTestHeader(int testNum, String msg) {
        System.out.println(
            "\n=================================================================");
        System.out.println("Test: " + testNum + ". " + msg + "\n");
    }
    public static void printTestTrailer() {
        System.out.println(
            "=================================================================\n");
    }

    public static void main(String[] args) {
        int testCount = 1;
        int element = 0;

        /*
         * PLEASE READ: uncomment each test as you go to help with debugging.
         * Note printArray calls the ArrayList methods: get(index) and size(),
         * so start off implementing the constructors, size, get, and add.
         *
         * After you complete all tests, comment out the line where we create an
         * ArrayListInteger and uncomment out the line where we create an
         * ArrayList<Integer>.  Do the same thing in printArray.  Verify you get
         * the same test results.
         */
        printTestHeader(
            testCount++,
            "create an ArrayList, add 2 elements, then call size()");
        // ArrayListInteger myList = new ArrayListInteger();
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("aaa");
        myList.add("bbb");
        int size = myList.size();
        System.out.println("size of array is: " + size);
        printArray("myList", myList);
        printTestTrailer();

        printTestHeader(testCount++, "test get(index)");
        System.out.println("element at 0: " + myList.get(0));
        System.out.println("element at 1: " + myList.get(1));
        printTestTrailer();

        printTestHeader(testCount++, "test remove(index) with 5 elements ");
        myList.add("ccc");
        myList.add("ddd");
        myList.add("eee");
        printArray("Before remove", myList);
        myList.remove(0); // remove first element
        printArray("after removing 1st element", myList);
        myList.remove(myList.size() - 1); // remove last element
        printArray("after removing last element", myList);
        myList.remove(1); // remove middle element
        printArray("after removing middle element", myList);

        myList.remove(0);
        myList.remove(0);
        printArray("after removing all elements", myList);

        printTestTrailer();

        printTestHeader(
            testCount++,
            "test adding past the original size of the internal array");
        for (int k = 0; k < 20; k++) {
            char ch = (char)('a' + k);
            myList.add("" + ch);
        }
        printArray(null, myList);
        printTestTrailer();

        printTestHeader(testCount++, "test add(index, element)");
        myList.add(0, "front");
        printArray("added \"front\" at offset 0", myList);
        myList.add(5, "middle");
        printArray("added \"middle\" at offset 5", myList);
        myList.add(myList.size(), "end");
        printArray("added \"end\" at the end", myList);
        printTestTrailer();

        printTestHeader(
            testCount++,
            "test set method, set 1st element to \"atZero\", last to \"lastOne\" ");
        String elm = myList.set(0, "atZero");
        System.out.println("previous value at 0 was: " + elm);
        elm = myList.set(myList.size() - 1, "lastOne");
        System.out.println("previous value at last index was: " + elm);
        printArray(null, myList);
        printTestTrailer();

        printTestHeader(
            testCount++,
            "test indexOf method, find specified element at which index ");
        int index = myList.indexOf("j");
        System.out.println("found element j at: " + index);
        index = myList.indexOf("atZero");
        System.out.println("found element atZero at: " + index);
        index = myList.indexOf("lastOne");
        System.out.println("found element lastOne at: " + index);
        index = myList.indexOf("notThere");
        System.out.println("found element notThere at: " + index);
        printTestTrailer();

        // note, in your add method, print out "Exception: invalid add".
        //       you will crash here when using the ArrayList<Integer>
        //       later today, we'll add a try/catch block to handle this
        //       condition

        // add some additional test cases
    }
}
