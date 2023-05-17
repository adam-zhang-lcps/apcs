public class NumberFrequency {
    private Integer number;
    private int count;

    public NumberFrequency(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Frequency of " + number + ": " + count;
    }

    public Integer getNumber() {
        return number;
    }

    public int getFrequency() {
        return count;
    }

    public void increment() {
        count++;
    }
}
