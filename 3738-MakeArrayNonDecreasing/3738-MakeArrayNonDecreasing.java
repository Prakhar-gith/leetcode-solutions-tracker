// Last updated: 8/30/2025, 6:56:17 PM
class Solution {
    public int maximumPossibleSize(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums) {
            int current = num;
            while (!stack.isEmpty() && current < stack.peekLast()) {
                current = Math.max(current, stack.pollLast());
            }
            stack.offerLast(current);
        }
        return stack.size();
    }
}