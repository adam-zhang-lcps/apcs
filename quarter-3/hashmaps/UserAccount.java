// Roses are red,
// violets are blue.
// My friends are null,
// zero, except for you.

public class UserAccount {
    private String username;
    private String password;
    private int friends;

    public UserAccount(String username, String password, int friends) {
        this.username = username;
        this.password = password;
        this.friends = friends;
    }

    public String getUsername() { return username; }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String getPassword() { return password; }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public int getFriends() { return friends; }

    public int setFriends(int friends) {
        this.friends = friends;
        return friends;
    }
}
