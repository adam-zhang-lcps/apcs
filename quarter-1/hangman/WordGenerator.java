package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordGenerator {

    public static String getWord() {
        Scanner file;
        try {
            String filename = "hangmanWords.txt";
            file = new Scanner(new File(filename));
            int nWords = file.nextInt();
            int cnt = (int)(Math.random() * nWords);

            System.out.println("picking from: " + filename +
                               " nWords: " + nWords + " offset: " + cnt);
            for (int k = 0; k < cnt; k++)
                file.next();

            String word = file.next();
            return word;
        } catch (FileNotFoundException e) {
            System.out.println("fileNotFound");
            return "file not found";
        }
    }
}
