import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class SearchesDriver {
  private static int amount = 1325;

  public static void main(String[] args) throws Exception {
    String[] text = input("declaration.txt");
    Arrays.sort(text);

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a word: ");
    String target = sc.next();
    int found = Searches.linear(text, target);
    if (found == -1)
      System.out.println(target + " was not found by the linear search.");
    else
      System.out.println("Linear Search found it at location " + found +
                         " in " + Searches.getLinearCount() + " comparisons.");
    int found2 = Searches.binary(text, target);
    if (found2 == -1)
      System.out.println(target + " was not found by the binary search.");
    else
      System.out.println("Binary Search found it at location " + found2 +
                         " in " + Searches.getBinaryCount() + " comparisons.");
  }

  // Reads the text file into a String array
  public static String[] input(String filename) throws Exception {
    Scanner infile = new Scanner(new File("assets/" + filename));
    String[] array = new String[amount];
    for (int k = 0; k < amount; k++)
      array[k] = infile.next();
    infile.close();
    return array;
  }
}
/////////////////////////////////////////////////////////
class Searches {
  private static int linearCount = 0;
  private static int binaryCount = 0;

  public static int getLinearCount() { return linearCount; }

  public static int getBinaryCount() { return binaryCount; }

  // Write a linear search that returns the index of the target or -1 if
  // it's not found
  public static int linear(String[] array, String target) {
    for (int i = 0; i < array.length; i++) {
      linearCount++;
      if (array[i].equals(target)) {
        return i;
      }
    }

    return -1;
  }

  // Write a binary search that returns the index of the target or -1 if
  // it's not found
  // This method should make a call to the binaryhelper...
  public static int binary(String[] array, String target) {
    return binaryHelper(array, target, 0, array.length - 1);
  }

  // Write the binaryHelper that returns the index of the target or -1 if
  // it's not found
  private static int binaryHelper(String[] array, String target, int start,
                                  int end) {
    if (start > end) {
      return -1;
    }

    int mid = (start + end) / 2;
    binaryCount++;
    int comparison = array[mid].compareTo(target);

    if (comparison == 0) {
      return mid;
    } else if (comparison < 0) {
      return binaryHelper(array, target, mid + 1, end);
    } else {
      return binaryHelper(array, target, start, mid - 1);
    }
  }
}
