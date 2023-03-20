import java.util.*;
import java.io.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename = "assets/declaration.txt";
        String outputFileName = "out.txt";

        try {
            Scanner infile = new Scanner(new File(filename));
            PrintStream outfile = new PrintStream(new FileOutputStream(outputFileName));

            processFile(infile, outfile);

            infile.close();
            outfile.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        sc.close();
    }

    public static String stripPunctuation(String word) {
        String newWord = "";
        for (int k = 0; k < word.length(); k++) {
            char ch = word.charAt(k);
            if (Character.isLetter(ch) || ch == '\'')
                newWord += word.charAt(k);
        }

        return newWord.toLowerCase();

    }

    public static void processFile(Scanner infile, PrintStream outfile) {
        // Declare and initialize a hashmap
        // Strings will be the keys and WordFrequency objects will be the values
        HashMap<String, WordFrequency> map = new HashMap<String, WordFrequency>();

        while (infile.hasNext()) { //repeats as long as the file has words left

            String word = infile.next().toLowerCase(); //retrieves the next word
            word = stripPunctuation(word); //removes the punctuation


            // either increment the object or
            // create a new WordFrequency object and add it to the hasmap
            if (map.containsKey(word)) {
                map.get(word).increment();
            } else {
                map.put(word, new WordFrequency(word));
            }
        }

        // create an arrayList of WordFrequency references
        ArrayList<WordFrequency> list = new ArrayList<>(map.values());

        // Use Collections.sort to sort the arraylist of WordFrequency objects
        Collections.sort(list);

        // Display your sorted array
        list.forEach(System.out::println);
    }
}
