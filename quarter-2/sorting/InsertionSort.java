//Programmer:  John Chapin  email: john.chapin@lcps.org
//Programmer: Chris Schroeder email: chris@4schroeders.com - Modified to use the ISortPanel

public class InsertionSort implements Runnable {
  int[] sortArray;
  ISortPanel panel;

  public InsertionSort(int[] anArray, ISortPanel aPanel) {
    sortArray = anArray;
    panel = aPanel;
  }
  
  // ***** SWAP CODE GOES HERE (PASS THE ARRAY REFERENCE and INDEXES) ****
  private void swap(int x, int y) {
    int t = sortArray[x];
    sortArray[x] = sortArray[y];
    sortArray[y] = t;
    panel.updateArray(sortArray);// Displays the whole array - put after a change is made
  }

  // *********  SORT ROUTINE GOES IN THE RUN METHOD
  @Override
  public void run() {
    for (int i = 1; i < sortArray.length; i++) {
      for (int j = i; j > 0; j--) {
        if (sortArray[j] < sortArray[j - 1]) {
          swap(j, j - 1);
        } else {
          break;
        }
      }
    }
  }// selectionSort() in run method
}// SelectionSort class
