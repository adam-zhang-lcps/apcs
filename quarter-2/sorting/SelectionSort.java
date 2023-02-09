// Programmer:  John Chapin  email: john.chapin@lcps.org
// Programmer: Chris Schroeder email: chris@4schroeders.com - Modified to use
// the ISortPanel Programmer: Adam Zhang

public class SelectionSort implements Runnable {
    int[] sortArray;
    ISortPanel panel;

    public SelectionSort(int[] anArray, ISortPanel aPanel) {
        sortArray = anArray;
        panel = aPanel;
    }

    // Write a swap helper method
    private void swap(int x, int y) {
        int t = sortArray[x];
        sortArray[x] = sortArray[y];
        sortArray[y] = t;
        panel.updateArray(
            sortArray); // this code goes after every change in the array
    }

    // This is the sort routine on sortArray
    @Override
    public void run() {
        for (int i = 0; i < sortArray.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < sortArray.length; j++) {
                if (sortArray[j] < sortArray[min]) {
                    min = j;
                }
            }
            swap(i, min);
        }

    } // selectionSort()
} // SelectionSort class
