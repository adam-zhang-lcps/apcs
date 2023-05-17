public class NumFreqApplication {
    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer, NumberFrequency>();

        for (int i = 0; i < 10_000_000; i++) {
            var num = (int) (Math.random() * 900) + 100;
            var freq = bst.get(num);
            if (freq == null) {
                freq = new NumberFrequency(num);
                bst.add(num, freq);
            }
            freq.increment();
        }

        var expectedFrequency = 10_000_000 / 900;
        var threePercent = (int) (expectedFrequency * 0.03);
        System.out.println("Expected frequency for each number: " + expectedFrequency);

        var outliers = 0;

        for (int i = 100; i <= 999; i++) {
            var freq = bst.get(i);
            if (freq == null) {
                freq = new NumberFrequency(i);
            }

            var isOutlier = Math.abs(expectedFrequency - freq.getFrequency()) > threePercent;

            System.out.println(freq + (isOutlier ? " (outlier)" : ""));
            if (isOutlier) {
                outliers++;
            }
        }

        System.out.println("Outliers: " + outliers);
        System.out.println("BST Depth: " + bst.getDepth());
    }
}
