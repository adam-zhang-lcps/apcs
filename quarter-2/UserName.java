import java.util.ArrayList;

public class UserName {
    // The list of possible user names, based on a user’s first and last names
    // and initialized by the constructor.

    /* to be implemented as part of part(a) */
    /**
     * Constructs a UserName object as described in part (a).
     *
     * Precondition: firstName and lastName have length greater than 0 and
     * contain only uppercase and lowercase letters.
     *
     */

    private ArrayList<String> possibleNames;

    public UserName(String firstName, String lastName) {
        possibleNames = new ArrayList<>();
        /* to be implemented in part (a) */
        for (int i = 1; i <= firstName.length(); i++) {
            possibleNames.add(lastName + firstName.substring(0, i));
        }
    }

    /** Returns true if arr contains name, and false otherwise. */

    public boolean isUsed(String name, String[] arr) {
        /* implementation not shown */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes strings from possibleNames that are found in usedNames as
     * described in part (b).
     *
     */

    public void setAvailableUserNames(String[] usedNames) {
        /* to be implemented in part (b) */
        possibleNames.removeIf(n -> isUsed(n, usedNames));
    }

    public String toString() {
        String outString = "";
        for (String name : possibleNames) {
            outString += name + "\n";
        }
        return outString;
    }
}
