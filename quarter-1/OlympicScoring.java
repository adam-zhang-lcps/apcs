import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OlympicScoring {

    public static void main(String[] args) {
        // set up judges array
        String[] judges = chooseJudges();

        ArrayList<Double> scores = getScores(judges);
        System.out.println("Scores before removal: " + scores);
        removeHighLow(scores);
        System.out.println("Scores after removal: " + scores);
        System.out.println("Average score: " + getAverage(scores));
    }

    public static ArrayList<Double> getScores(String[] judges) {
        ArrayList<Double> scores = new ArrayList<Double>();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < judges.length; i++) {
            // don't want a new line after the prompt, so use print instead of
            // println
            System.out.print("Enter score for judge: " + judges[i] + " = ");
            double score = input.nextDouble();
            scores.add(score);
        }
        // remember to close the Scanner object
        input.close();
        return scores;
    }

    public static void removeHighLow(ArrayList<Double> scores) {
        // convenient method to find the max and min of a list
        double max = Collections.max(scores);
        double min = Collections.min(scores);

        // remove can take an index or an object
        // if an object is passed, it removes the
        // object that equals() the one passed
        // so we can use the max and min variables,
        // which get casted to Double objects
        scores.remove(max);
        scores.remove(min);

        System.out.println("Score removed: " + max);
        System.out.println("Score removed: " + min);

        // objects get passed by reference, so no need to return anything
        // the ArrayList scores is modified in place
    }

    public static Double getAverage(ArrayList<Double> scores) {
        // one-liner to get the average of a list
        return scores.stream().mapToDouble(c -> c).average().getAsDouble();
    }

    // Completed for you do not modify
    public static String[] chooseJudges() {
        String[] judges = new String[(int)(Math.random() * 3 + 5)];
        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Germany");
        countries.add("USA");
        countries.add("Canada");
        countries.add("Botswana");
        countries.add("China");
        countries.add("France");
        countries.add("Eritrea");
        countries.add("Russia");
        countries.add("Mexico");
        countries.add("Belgium");
        for (int i = 0; i < judges.length; i++) {
            int choice = (int)(Math.random() * countries.size());
            judges[i] = countries.get(choice);
            countries.remove(choice);
        }
        return judges;
    }
}
