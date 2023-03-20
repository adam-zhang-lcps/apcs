// When I first laid eyes on you,
// I knew we'd be greater as two.
// Never would I have thought
// we would be together forever not.
// The day the sky burned bright blood,
// the day our world was cut,
// was when I lost it all.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashmapTester {
    public static boolean logIn(String username, String password,
                                AccountHashmap map) {
        UserAccount account = map.get(username);
        if (account == null) {
            return false;
        }
        return account.getPassword().equals(password);
    }

    public static AccountHashmap populateHashmap(String filename) {
        // Example line:
        // "dScott ayfunkvqknrz 846"
        AccountHashmap map = new AccountHashmap((int)(0.7 * 1500.0));
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String username = parts[0];
                String password = parts[1];
                int friends = Integer.parseInt(parts[2]);
                UserAccount account =
                    new UserAccount(username, password, friends);
                map.put(username, account);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.exit(1);
        }
        return map;
    }

    public static void main(String[] args) {
        AccountHashmap map = populateHashmap("assets/AccountData.txt");
        String[][] testCases = new String[][] {
            {"dScott", "ayfunkvqknrz"},
            {"rPitt", "tzdtexqyte"},
            {"xRivera", "mrrhzymlobn"},
            {"abcde", "abcde"}, // purposely nonexistent
            {"dScott", "abcde"} // purposely wrong password
        };

        for (String[] test : testCases) {
            UserAccount account = map.get(test[0]);
            if (account == null) {
                System.out.println("Account not found: " + test[0]);
            } else {
                System.out.println("Account found: " + test[0]);
                System.out.println("Login: " + logIn(test[0], test[1], map));
            }
        }
    }
}
