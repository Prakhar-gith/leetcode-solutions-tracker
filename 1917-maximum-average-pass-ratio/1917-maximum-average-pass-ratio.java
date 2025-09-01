import java.util.PriorityQueue;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            double profitA = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double profitB = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return Double.compare(profitB, profitA);
        });

        for (int[] c : classes) {
            pq.offer(new double[]{ (double) c[0], (double) c[1] });
        }

        for (int i = 0; i < extraStudents; i++) {
            double[] currentClass = pq.poll();
            double pass = currentClass[0] + 1;
            double total = currentClass[1] + 1;
            pq.offer(new double[]{pass, total});
        }

        double totalRatio = 0;
        while (!pq.isEmpty()) {
            double[] c = pq.poll();
            totalRatio += c[0] / c[1];
        }

        return totalRatio / classes.length;
    }
}