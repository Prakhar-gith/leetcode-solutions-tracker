// Last updated: 8/30/2025, 6:56:55 PM
class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer((long) num);
        }
        int operations = 0;
        while (pq.size() >= 2 && pq.peek() < k) {
            long x = pq.poll();
            long y = pq.poll();
            long newVal = 2 * x + y;
            pq.offer(newVal);
            operations++;
        }
        return operations;
    }
}