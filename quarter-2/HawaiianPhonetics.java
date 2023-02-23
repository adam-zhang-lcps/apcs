import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HawaiianPhonetics {
    private static final Map<String, String> DOUBLE_REPLACEMENTS =
        new LinkedHashMap<>();
    private static final Map<String, String> SINGLE_REPLACEMENTS =
        new LinkedHashMap<>();

    private static void initMap() {
        DOUBLE_REPLACEMENTS.put("ai", "eye-");
        DOUBLE_REPLACEMENTS.put("ae", "eye-");
        DOUBLE_REPLACEMENTS.put("ao", "ow-");
        DOUBLE_REPLACEMENTS.put("au", "ow-");
        DOUBLE_REPLACEMENTS.put("ei", "ay-");
        DOUBLE_REPLACEMENTS.put("eu", "eh-oh-");
        DOUBLE_REPLACEMENTS.put("iu", "ew-");
        DOUBLE_REPLACEMENTS.put("oi", "oy-");
        DOUBLE_REPLACEMENTS.put("ou", "ow-");
        DOUBLE_REPLACEMENTS.put("ui", "ooey-");

        SINGLE_REPLACEMENTS.put("a", "ah-");
        SINGLE_REPLACEMENTS.put("e", "eh-");
        SINGLE_REPLACEMENTS.put("i", "ee-");
        SINGLE_REPLACEMENTS.put("o", "oh-");
        SINGLE_REPLACEMENTS.put("u", "oo-");
    }

    public static boolean validateWord(String word) {
        String lower = word.toLowerCase();
        String invalidChars = "";
        for (int i = 0; i < lower.length(); i++) {
            if ("pkhlmnwaeiou '".indexOf(lower.charAt(i)) == -1) {
                invalidChars += lower.charAt(i);
            }
        };
        if (invalidChars.length() > 0) {
            System.out.println(
                "The following are not valid Hawaiian letters: " +
                invalidChars);
            return false;
        }
        return true;
    }
    public static String generatePhonetic(String word) {
        String phonetic = word.toLowerCase();
        String result = "";

        int i = 0;
        while (i < phonetic.length()) {
            // This is so ugly
            String doubleReplacement = "";
            String singleReplacement = "";
            if (i < phonetic.length() - 1) {
                doubleReplacement = phonetic.substring(i, i + 2);
            }
            if (i < phonetic.length()) {
                singleReplacement = phonetic.substring(i, i + 1);
            }
            if (DOUBLE_REPLACEMENTS.containsKey(doubleReplacement)) {
                result += DOUBLE_REPLACEMENTS.get(doubleReplacement);
                i += 2;
            } else if (SINGLE_REPLACEMENTS.containsKey(singleReplacement)) {
                result += SINGLE_REPLACEMENTS.get(singleReplacement);
                i += 1;
            } else if (singleReplacement.equals("w")) {
                // Whatever, hardcode w
                result += switch (phonetic.charAt(i - 1)) {
                    case 'i', 'e' -> "v";
                    default -> "w";
                };
                i += 1;
            } else {
                if (singleReplacement.equals(" ") && result.endsWith("-")) {
                    result = result.substring(0, result.length() - 1);
                }
                result += singleReplacement;
                i += 1;
            }
        }

        if (result.endsWith("-")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
    public static void main(String[] args) {
        initMap();

        String input;
        Scanner s = new Scanner(System.in);
        do {
            do {
                System.out.print("Enter a word: ");
                input = s.nextLine();
            } while (!validateWord(input));
            System.out.println(generatePhonetic(input));
            System.out.print(
                "Would you like to enter another word? (y/n/yes/no): ");
            input = s.nextLine();
        } while (input.equals("y") || input.equals("yes"));

        s.close();
    }
}
