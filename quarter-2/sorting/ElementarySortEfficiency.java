import java.util.Random;
import java.util.Scanner;

public class ElementarySortEfficiency {
  // prints out the given array in a clean format
  public static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  // swaps elements a and b in the given array
  public static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  public static void selectionSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int min = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[min]) {
          min = j;
        }
      }
      swap(array, i, min);
    }
  }

  public static void insertionSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int j = i;
      while (j > 0 && array[j] < array[j - 1]) {
        swap(array, j, j - 1);
        j--;
      }
    }
  }

  // merge sort code
  // Credit: https://www.geeksforgeeks.org/merge-sort/
  public static void merge(int arr[], int l, int m, int r) {
    // Find sizes of two subarrays to be merged
    int n1 = m - l + 1;
    int n2 = r - m;

    /* Create temp arrays */
    int L[] = new int[n1];
    int R[] = new int[n2];

    /*Copy data to temp arrays*/
    for (int i = 0; i < n1; ++i)
      L[i] = arr[l + i];
    for (int j = 0; j < n2; ++j)
      R[j] = arr[m + 1 + j];

    /* Merge the temp arrays */

    // Initial indexes of first and second subarrays
    int i = 0, j = 0;

    // Initial index of merged subarry array
    int k = l;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    /* Copy remaining elements of L[] if any */
    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    /* Copy remaining elements of R[] if any */
    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  // Main function that sorts arr[l..r] using
  // merge()
  public static void sort(int arr[], int l, int r) {
    if (l < r) {
      // Find the middle point
      int m = (l + r) / 2;

      // Sort first and second halves
      sort(arr, l, m);
      sort(arr, m + 1, r);

      // Merge the sorted halves
      merge(arr, l, m, r);
    }
  }

  // time efficiency tester -> size = size of array, sort = 1 for selection, 2
  // for insertion
  public static void timeTest1(int size, int sort) {
    // Generate an array of random numbers, sort it, print time elapsed in
    // miliseconds
    int[] array = new int[size];
    Random r = new Random(); // another way of generating random numbers
    for (int i = 0; i < array.length; i++) {
      array[i] = r.nextInt(size);
    }

    long startTime = System.nanoTime();
    if (sort == 1) {
      selectionSort(array);
    } else if (sort == 2) {
      insertionSort(array);
    } else {
      sort(array, 0, array.length - 1);
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000; // time in milliseconds
    System.out.println("Time Elapsed: " + duration + " milliseconds");
  }

  // time efficiency tester -> size = size of array, sort = 1 for selection, 2
  // for insertion
  public static void timeTest2(int size, int sort) {
    // Generate an array of numbers, sort it, print time elapsed in milliseconds
    int[] array = new int[size];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }

    long startTime = System.nanoTime();
    if (sort == 1) {
      selectionSort(array);
    } else if (sort == 2) {
      insertionSort(array);
    } else {
      sort(array, 0, array.length - 1); // merge
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000; // time in miliseconds
    System.out.println("Time Elapsed: " + duration + " milliseconds");
  }

  public static void main(String[] args) {
    // gets input and calls methods
    Scanner s = new Scanner(System.in);
    System.out.println("What should the size of the array be?");
    int size = s.nextInt();
    System.out.println("Which sorting algorithm should be used?"
                       + "\n1 = Selection, 2 = Insertion, 3 = Merge");
    int algorithm = s.nextInt();
    if (!(algorithm > 0 && algorithm < 4)) {
      algorithm = 1;
    }
    System.out.println("Should the array be randomized or already in order?"
                       + "\n1 = Randomized, 2 = In Order");
    int state = s.nextInt();
    if (state == 2) {
      timeTest2(size, algorithm);
    } else {
      timeTest1(size, algorithm);
    }
    s.close();
  }
}
