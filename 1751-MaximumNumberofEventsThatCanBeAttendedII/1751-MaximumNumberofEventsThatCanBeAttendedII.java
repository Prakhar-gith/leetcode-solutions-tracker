// Last updated: 7/9/2025, 12:40:23 AM
class Solution {
    // memo[i][j] stores the maximum value considering events from index i onwards, with j events allowed
    private int[][] memo;
    private int[][] events;
    private int n;

    public int maxValue(int[][] events, int k) {
        // Sort events by startDay
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        this.events = events;
        this.n = events.length;
        this.memo = new int[n + 1][k + 1];

        // Initialize memoization table with -1 (or any indicator for not computed)
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(0, k);
    }

    private int solve(int index, int remainingEvents) {
        // Base cases
        if (remainingEvents == 0 || index == n) {
            return 0;
        }

        // Check if already computed
        if (memo[index][remainingEvents] != -1) {
            return memo[index][remainingEvents];
        }

        // Option 1: Don't attend the current event (events[index])
        int maxVal = solve(index + 1, remainingEvents);

        // Option 2: Attend the current event (events[index])
        // Find the next non-overlapping event using binary search
        int currentEventEndDay = events[index][1];
        int nextIndex = findNextEvent(currentEventEndDay);

        maxVal = Math.max(maxVal, events[index][2] + solve(nextIndex, remainingEvents - 1));

        // Store and return the result
        return memo[index][remainingEvents] = maxVal;
    }

    // Binary search to find the first event whose startDay is greater than endDay
    private int findNextEvent(int endDay) {
        int low = 0;
        int high = n - 1;
        int nextEventIndex = n; // Default to n if no suitable event found

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > endDay) {
                nextEventIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return nextEventIndex;
    }
}