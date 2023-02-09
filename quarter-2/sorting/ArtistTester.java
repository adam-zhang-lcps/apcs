import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ArtistTester {
    // array of artists
    private static Artist[] myArtistArray;

    // file reading
    public static void readFile(String fileName) {
        Scanner infile = null;
        int numOfArtists = 0;

        try {
            infile = new Scanner(new File(fileName));
        } catch (IOException e) {
            System.out.println("oops");
        }

        // read in the number of objects on first line
        if (infile.hasNext()) {
            numOfArtists = infile.nextInt();
            infile.nextLine();
        } else {
            System.out.println("file is empty!");
        }
        myArtistArray = new Artist[numOfArtists];

        // read in each object
        for (int index = 0; index < numOfArtists; index++) {
            // Artists have 3 data fields
            // 3 input lines for each Artist
            String artistName = "";
            String songName = "";
            Long songSales = 0L;

            if (infile.hasNext()) {
                artistName = infile.nextLine();
            } else {
                System.out.println("Infile does not have artist name.");
                break;
            }

            if (infile.hasNext()) {
                songName = infile.nextLine();
            } else {
                System.out.println("Infile does not have song name.");
                break;
            }

            if (infile.hasNext()) {
                songSales = infile.nextLong();
                if (infile.hasNext())
                    infile.nextLine();
            } else {
                System.out.println("Infile does not have sales numbers.");
                break;
            }
            myArtistArray[index] = new Artist(artistName, songName, songSales);

        } // for loop for each artist
        infile.close();
    }

    // sorting
    public static void swap(Comparable[] sortArray, int index1, int index2) {
        Comparable temp = sortArray[index1];
        sortArray[index1] = sortArray[index2];
        sortArray[index2] = temp;
    }

    public static void sort(Comparable[] sortArray) {
        int compareIndex = 0;
        for (int i = 1; i < sortArray.length; i++) {
            compareIndex = i - 1;
            while (compareIndex >= 0 && sortArray[compareIndex].compareTo(
                                            sortArray[compareIndex + 1]) > 0) {
                swap(sortArray, compareIndex, compareIndex + 1);
                compareIndex--;
            }
        }
    }

    public static void main(String[] args) {
        // read file, change parameter to change file read
        readFile("assets/artist.txt");

        // sort type
        Scanner s = new Scanner(System.in);
        System.out.println(
            "Sort by...? (1 = sales up, 2 = sales down, 3 = name)");
        int choice = s.nextInt();
        Artist.setSortType(choice);

        // sort the array
        sort(myArtistArray);

        // print the array
        for (int i = 0; i < myArtistArray.length; i++) {
            System.out.println(myArtistArray[i]);
        }
    }
}
