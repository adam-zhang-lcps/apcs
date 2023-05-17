public class WordFrequency implements Comparable<WordFrequency> {
    private String word;
    private int frequency;

    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 1;
    }

    public String getWord() { return word; }

    public int getFrequency() { return frequency; }

    public void increment() { frequency++; }

    @Override
    public String toString() {
        return word + ":\t" + frequency;
    }

    @Override
    public int compareTo(WordFrequency other) {
        int Δf = other.frequency - this.frequency;
        return Δf == 0 ? this.word.compareTo(other.word) : Δf;
    }
}
