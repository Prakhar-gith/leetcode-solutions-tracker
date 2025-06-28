// Last updated: 6/28/2025, 9:16:40 PM
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Step 1: Create pairs of (value, original_index)
        int n = nums.length;
        int[][] indexedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexedNums[i][0] = nums[i]; // value
            indexedNums[i][1] = i;       // original index
        }

        // Step 2 & 3: Sort by value (descending) and select top k.
        // A min-priority queue of size k can be used to efficiently find the k largest elements.
        // We store (value, original_index) in the priority queue.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] indexedNum : indexedNums) {
            if (minHeap.size() < k) {
                minHeap.offer(indexedNum);
            } else if (indexedNum[0] > minHeap.peek()[0]) {
                minHeap.poll();
                minHeap.offer(indexedNum);
            }
        }

        // Step 4: Extract the elements and sort by original index
        int[][] resultPairs = new int[k][2];
        int i = 0;
        while (!minHeap.isEmpty()) {
            resultPairs[i++] = minHeap.poll();
        }

        // Sort by original index to maintain the subsequence order
        Arrays.sort(resultPairs, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 5: Extract values to form the final subsequence
        int[] subsequence = new int[k];
        for (int j = 0; j < k; j++) {
            subsequence[j] = resultPairs[j][0];
        }

        return subsequence;
    }
}