import java.util.ArrayList;

public class Password {
    private String password;
    private boolean isValid;
    private String invalidReason;

    public Password() {
        password = "Abc@123";
        validate();
    }

    public Password(String password) {
        this.password = password;
        validate();
    }

    private void validate() {
        ArrayList<String> invalidReasons = new ArrayList<>();
        if (password.length() <= 5) {
            invalidReason = "too short";
            isValid = false;
            return;
        }
        if (password.length() >= 17) {
            invalidReason = "too long";
            isValid = false;
            return;
        }
        if (password.chars().allMatch(c -> !Character.isLowerCase(c))) {
            invalidReasons.add("no lower case letters");
        }
        if (password.chars().allMatch(c -> !Character.isUpperCase(c))) {
            invalidReasons.add("no upper case letters");
        }
        if (password.indexOf(' ') != -1) {
            invalidReasons.add("contains spaces");
        }
        if (password.chars().allMatch(c -> !Character.isDigit(c))) {
            invalidReasons.add("no digits");
        }
        if (password.chars().allMatch(c -> Character.isLetterOrDigit(c) || c == ' ')) {
            invalidReasons.add("no special characters");
        }

        invalidReason = String.join(", ", invalidReasons);
        isValid = invalidReasons.isEmpty();
    }

    public void changePassword(String password) {
        this.password = password;
        validate();
    }

    public boolean isValid() {
        return isValid;
    }

    public String getInvalidReason() {
        return invalidReason;
    }
}
