// Last updated: 8/30/2025, 6:56:28 PM
class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        // For each query, record its [l, r].
        // We'll sort queries by l ascending.
        int[][] intervals = new int[m][3]; // {l, r, originalIndex}
        for (int i = 0; i < m; i++) {
            intervals[i][0] = queries[i][0];
            intervals[i][1] = queries[i][1];
            intervals[i][2] = i;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // coverDiff will track the coverage difference array,
        // so that cover[j] = prefix sum of coverDiff up to j.
        int[] coverDiff = new int[n + 1];

        // A max-heap of intervals currently available (those whose l <= current j),
        // ordered by furthest right endpoint (r) first.
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );

        int ptr = 0;          // pointer over sorted intervals
        int usedCount = 0;    // how many intervals we've chosen

        // current running coverage at position j
        int runningCover = 0;

        for (int j = 0; j < n; j++) {
            // 1) incorporate new intervals that start at or before j
            while (ptr < m && intervals[ptr][0] <= j) {
                pq.offer(intervals[ptr]);
                ptr++;
            }
            // 2) update runningCover from diff
            runningCover += coverDiff[j];

            // 3) If we need more coverage at j, greedily pick intervals
            //    that cover j, choosing the one with the farthest r.
            while (runningCover < nums[j]) {
                // remove any intervals from pq that no longer cover j
                while (!pq.isEmpty() && pq.peek()[1] < j) {
                    pq.poll();
                }
                // if none can cover j, impossible
                if (pq.isEmpty()) return -1;
                // pick the interval with the largest r
                int[] best = pq.poll();
                int r = best[1];
                // select this interval: it increments coverage by 1 on [j..r]
                usedCount++;
                runningCover++;           // it covers position j
                coverDiff[r + 1]--;       // coverage decrement after r
            }
        }

        // We managed to cover every index j sufficiently.
        // We used usedCount queries, so we can remove the other m - usedCount.
        return m - usedCount;
    }
}
