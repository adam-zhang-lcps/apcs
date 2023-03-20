// Roses are red,
// violets are blue,
// open-addressing is hard,
// and I'm too lazy to.

import java.util.LinkedList;

public class AccountHashmap {
    private LinkedList<UserAccount>[] data;
    private int size;

    public AccountHashmap(int capacity) {
        data = new LinkedList[capacity];
        size = 0;
    }

    public UserAccount get(String key) {
        int index = hash(key);
        if (data[index] == null) {
            return null;
        }
        for (UserAccount account : data[index]) {
            if (account.getUsername().equals(key)) {
                return account;
            }
        }
        return null;
    }

    public void put(String key, UserAccount value) {
        int index = hash(key);
        if (data[index] == null) {
            data[index] = new LinkedList<>();
        }
        data[index].add(value);
        size++;
    }

    public UserAccount remove(String key) {
        int index = hash(key);
        if (data[index] == null) {
            return null;
        }
        for (UserAccount account : data[index]) {
            if (account.getUsername().equals(key)) {
                data[index].remove(account);
                size--;
                return account;
            }
        }
        return null;
    }

    private int hash(String key) { return key.chars().sum() % data.length; }
}
